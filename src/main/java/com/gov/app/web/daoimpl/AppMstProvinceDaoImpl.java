package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppMstProvinceDao;
import com.gov.app.web.model.AppMstProvince;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("province")
public class AppMstProvinceDaoImpl extends AbstractDao<Integer, AppMstProvince> implements AppMstProvinceDao {

    @Override
    @Transactional
    public AppMstProvince findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstProvince(AppMstProvince province) {
        persist(province);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppMstProvince province) {
        super.saveOrUpdate(province);
    }

    @Override
    @Transactional
    public void deleteAppMstProvinceByCode(String code) {
        Query query = getSession().createSQLQuery("delete from AppMstProvince where code = :code");
        query.setString("code", code);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstProvince> findAllAppMstProvinces() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<AppMstProvince>) criteria.list();
    }

    @Override
    public AppMstProvince findAppMstProvinceByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (AppMstProvince) criteria.uniqueResult();
    }

}
