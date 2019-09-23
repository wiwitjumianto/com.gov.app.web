package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppMstVillageDao;
import com.gov.app.web.model.AppMstDistrict;
import com.gov.app.web.model.AppMstVillage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("village")
public class AppMstVillageDaoImpl extends AbstractDao<Integer, AppMstVillage> implements AppMstVillageDao {

    @Override
    @Transactional
    public AppMstVillage findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstVillage(AppMstVillage villageparam) {
        persist(villageparam);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppMstVillage villageparam) {
        super.saveOrUpdate(villageparam);
    }

    @Override
    @Transactional
    public void deleteAppMstVillageByCode(String code) {
        Query query = getSession().createSQLQuery("delete from AppMstVillage where code = :code");
        query.setString("code", code);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstVillage> findAllAppMstVillages() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<AppMstVillage>) criteria.list();
    }

    @Override
    public AppMstVillage findAppMstVillageByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (AppMstVillage) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstVillage> findAppMstVillageByDistrict(int districtId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.eq("appMstDistrictId", districtId)));
        return (List<AppMstVillage>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstVillage> findByProvinceList(Collection<Integer> listProvinceId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.in("appMstProvinceId", listProvinceId)));
        return (List<AppMstVillage>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstVillage> findByCityList(Collection<Integer> listCityId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.in("appMstCityId", listCityId)));
        return (List<AppMstVillage>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstVillage> findByDistrictList(Collection<Integer> istDistrictId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.in("appMstDistrictId", istDistrictId)));
        return (List<AppMstVillage>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstVillage> findByVillageList(Collection<Integer> listVillageId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.in("appMstVillageId", listVillageId)));
        criteria.addOrder(Order.asc("appMstProvinceId"));
        return (List<AppMstVillage>) criteria.list();
    }

    @Override
    @Transactional
    public List<Integer> findByDistictProvince(Collection<Integer> villageId) {
        Query query = getSession().createSQLQuery("select DISTINCT(app_mst_province_id)"
                + " from app_mst_village where APP_MST_VILLAGE_ID in(:listVillage)").setParameterList("listVillage", villageId);

        List<Integer> data = new ArrayList();
        data = query.list();
        return data;
    }

    @Override
    @Transactional
    public List<Integer> findByDistictCity(Collection<Integer> villageId) {
        Query query = getSession().createSQLQuery("select DISTINCT(app_mst_city_id)"
                + " from app_mst_village where APP_MST_VILLAGE_ID in(:listVillage) "
                + "order by app_mst_province_id asc").setParameterList("listVillage", villageId);

        List<Integer> data = new ArrayList();
        data = query.list();
        return data;
    }

}
