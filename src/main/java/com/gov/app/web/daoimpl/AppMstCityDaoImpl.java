package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppMstCityDao;
import com.gov.app.web.model.AppMstCity;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("city")
public class AppMstCityDaoImpl extends AbstractDao<Integer, AppMstCity> implements AppMstCityDao {

    @Override
    @Transactional
    public AppMstCity findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstCity(AppMstCity province) {
        persist(province);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppMstCity province) {
        super.saveOrUpdate(province);
    }

    @Override
    @Transactional
    public void deleteAppMstCityByCode(String code) {
        Query query = getSession().createSQLQuery("delete from AppMstCity where code = :code");
        query.setString("code", code);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstCity> findAllAppMstCitys() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<AppMstCity>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstCity> findAppMstCityByProvince(int appMstprovinceId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.eq("appMstProvinceId", appMstprovinceId)));
        return (List<AppMstCity>) criteria.list();
    }

}
