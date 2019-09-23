package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppMstDistrictDao;
import com.gov.app.web.model.AppMstDistrict;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("district")
public class AppMstDistrictDaoImpl extends AbstractDao<Integer, AppMstDistrict> implements AppMstDistrictDao {

    @Override
    @Transactional
    public AppMstDistrict findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstDistrict(AppMstDistrict distroicten) {
        persist(distroicten);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppMstDistrict distroicten) {
        super.saveOrUpdate(distroicten);
    }

    @Override
    @Transactional
    public void deleteAppMstDistrictByCode(String code) {
        Query query = getSession().createSQLQuery("delete from AppMstDistrict where code = :code");
        query.setString("code", code);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDistrict> findAllAppMstDistricts() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<AppMstDistrict>) criteria.list();
    }

    @Override
    public AppMstDistrict findAppMstDistrictByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (AppMstDistrict) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstDistrict> findAppMstDistrictByCity(int cityId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.eq("appMstCityId", cityId)));
        return (List<AppMstDistrict>) criteria.list();
    }

}
