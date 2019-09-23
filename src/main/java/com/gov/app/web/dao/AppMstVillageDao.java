package com.gov.app.web.dao;

import com.gov.app.web.model.AppMstDistrict;
import com.gov.app.web.model.AppMstVillage;
import java.util.Collection;
import java.util.List;

public interface AppMstVillageDao {

    AppMstVillage findById(int id);

    void saveAppMstVillage(AppMstVillage student);

    public void saveOrUpdate(AppMstVillage student);

    void deleteAppMstVillageByCode(String ssn);

    List<AppMstVillage> findAllAppMstVillages();

    AppMstVillage findAppMstVillageByCode(String ssn);

    List<AppMstVillage> findAppMstVillageByDistrict(int districtId);

    public List<AppMstVillage> findByProvinceList(Collection<Integer> listProvinceId);

    public List<AppMstVillage> findByCityList(Collection<Integer> listProvinceId);

    public List<AppMstVillage> findByDistrictList(Collection<Integer> listProvinceId);

    public List<AppMstVillage> findByVillageList(Collection<Integer> listProvinceId);

    public List<Integer> findByDistictProvince(Collection<Integer> villageId);
    
    public List<Integer> findByDistictCity(Collection<Integer> villageId);

}
