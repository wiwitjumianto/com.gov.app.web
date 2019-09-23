/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.common;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 *
 * @author Reza Dwi Saputra <reza.dwi@bni.co.id>
 */
public class DataTableRequest {

    private int draw;
    private List<DataTableRequestColumn> columns;
    private List<DataTableRequestOrder> order;
    private int start;
    private int length;
    private DataTableRequestSearch search;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public List<DataTableRequestColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTableRequestColumn> columns) {
        this.columns = columns;
    }

    public List<DataTableRequestOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DataTableRequestOrder> order) {
        this.order = order;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public DataTableRequestSearch getSearch() {
        return search;
    }

    public void setSearch(DataTableRequestSearch search) {
        this.search = search;
    }

    public DataTableResult toDataSourceResult(Session session, Class<?> clazz) {
        Criteria criteria = session.createCriteria(clazz);

        filter(criteria, null, clazz);
        long total = total(criteria);

        filter(criteria, getColumns(), clazz);
        long totalFiltered = total(criteria);

        criteria.setFirstResult(getStart());
        criteria.setMaxResults(getLength());

        order(criteria, getOrder());
        criteria.add(Restrictions.eq("deletedStatus", 0));
        DataTableResult result = new DataTableResult();
        result.setData(criteria.list());
        result.setDraw(getDraw() + "");
        result.setRecordsFiltered(totalFiltered);
        result.setRecordsTotal(total);
        return result;
    }

    private void filter(Criteria criteria, List<DataTableRequestColumn> columns, Class<?> clazz) {
        if (columns != null && !columns.isEmpty()) {
            Junction junction = Restrictions.conjunction();

            if (!getSearch().getValue().isEmpty()) {
                junction = Restrictions.disjunction();
            }

            for (DataTableRequestColumn column : columns) {
                if (column.getData() != null
                        && column.getSearchable()) {
                    restrict(junction, column, clazz);
                }
            }

            criteria.add(junction);
        }
    }

    private void restrict(Junction junction, DataTableRequestColumn column, Class<?> clazz) {
        if (column.getSearch().getValue().isEmpty()
                && getSearch().getValue().isEmpty()) {
            return;
        }

        Operator operator = Operator.eq;

        DataTableRequestSearch search = column.getSearch();

        if (!getSearch().getValue().isEmpty()) {
            search = getSearch();
        }

        String field = column.getData().toString();
        Object value = search.getValue();
        boolean ignoreCase = true;

        try {
            Class<?> type = new PropertyDescriptor(field, clazz).getPropertyType();
            if (type == double.class || type == Double.class) {
                value = Double.parseDouble(value.toString());
            } else if (type == float.class || type == Float.class) {
                value = Float.parseFloat(value.toString());
            } else if (type == long.class || type == Long.class) {
                value = Long.parseLong(value.toString());
            } else if (type == int.class || type == Integer.class) {
                value = Integer.parseInt(value.toString());
            } else if (type == short.class || type == Short.class) {
                value = Short.parseShort(value.toString());
            } else if (type == boolean.class || type == Boolean.class) {
                value = Boolean.parseBoolean(value.toString());
            } else if (type == BigInteger.class) {
                value = new BigInteger(value.toString());
            } else if (type == BigDecimal.class) {
                value = new BigDecimal(value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        switch (operator) {
            case eq:
                if (value instanceof String) {
//                    junction.add(Restrictions.ilike(field, value.toString(), MatchMode.EXACT));
                    junction.add(getLikeExpression(field, value.toString(), MatchMode.ANYWHERE, ignoreCase));
                } else {
                    junction.add(Restrictions.eq(field, value));
                }
                break;
            case neq:
                if (value instanceof String) {
                    junction.add(Restrictions.not(Restrictions.ilike(field, value.toString(), MatchMode.EXACT)));
                } else {
                    junction.add(Restrictions.ne(field, value));
                }
                break;
            case gt:
                junction.add(Restrictions.gt(field, value));
                break;
            case gte:
                junction.add(Restrictions.ge(field, value));
                break;
            case lt:
                junction.add(Restrictions.lt(field, value));
                break;
            case lte:
                junction.add(Restrictions.le(field, value));
                break;
            case startswith:
                junction.add(getLikeExpression(field, value.toString(), MatchMode.START, ignoreCase));
                break;
            case endswith:
                junction.add(getLikeExpression(field, value.toString(), MatchMode.END, ignoreCase));
                break;
            case contains:
                junction.add(getLikeExpression(field, value.toString(), MatchMode.ANYWHERE, ignoreCase));
                break;
            case doesnotcontain:
                junction.add(Restrictions.not(Restrictions.ilike(field, value.toString(), MatchMode.ANYWHERE)));
                break;
        }

    }

    private Criterion getLikeExpression(String field, String value, MatchMode mode, boolean ignoreCase) {
        SimpleExpression expression = Restrictions.like(field, value, mode);

        if (ignoreCase == true) {
            expression = expression.ignoreCase();
        }

        return expression;
    }

    private long total(Criteria criteria) {
        long total = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);

        return total;
    }

    private void order(Criteria criteria, List<DataTableRequestOrder> order) {
        if (order != null) {
            for (DataTableRequestOrder o : order) {
                if (o.getDir().equalsIgnoreCase("asc")) {
                    criteria.addOrder(Order.asc(getColumns().get(o.getColumn()).getData().toString()));
                } else if (o.getDir().equalsIgnoreCase("desc")) {
                    criteria.addOrder(Order.desc(getColumns().get(o.getColumn()).getData().toString()));
                }
            }
        }
    }

    public static class DataTableRequestColumn {

        private Object data;
        private String name;
        private Boolean searchable;
        private Boolean orderable;
        private DataTableRequestSearch search;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getSearchable() {
            return searchable;
        }

        public void setSearchable(Boolean searchable) {
            this.searchable = searchable;
        }

        public Boolean getOrderable() {
            return orderable;
        }

        public void setOrderable(Boolean orderable) {
            this.orderable = orderable;
        }

        public DataTableRequestSearch getSearch() {
            return search;
        }

        public void setSearch(DataTableRequestSearch search) {
            this.search = search;
        }

    }

    public static class DataTableRequestOrder {

        private int column;
        private String dir;

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

    }

    public static class DataTableRequestSearch {

        private String value;
        private Boolean regex;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Boolean getRegex() {
            return regex;
        }

        public void setRegex(Boolean regex) {
            this.regex = regex;
        }

    }

}
