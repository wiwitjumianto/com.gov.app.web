package com.gov.app.web.dao;

import com.gov.app.web.model.AppMstDistrict;
import java.util.List;

public interface AppMstDistrictDao {

    AppMstDistrict findById(int id);

    void saveAppMstDistrict(AppMstDistrict student);

    public void saveOrUpdate(AppMstDistrict student);

    void deleteAppMstDistrictByCode(String ssn);

    List<AppMstDistrict> findAllAppMstDistricts();

    AppMstDistrict findAppMstDistrictByCode(String ssn);

    List<AppMstDistrict> findAppMstDistrictByCity(int cityId);

}
