package com.gov.app.web.dao;

import com.gov.app.web.model.AppMstCity;
import java.util.List;

public interface AppMstCityDao {

    AppMstCity findById(int id);

    void saveAppMstCity(AppMstCity student);

    public void saveOrUpdate(AppMstCity student);

    void deleteAppMstCityByCode(String ssn);

    List<AppMstCity> findAllAppMstCitys();

    List<AppMstCity> findAppMstCityByProvince(int appMstprovinceId);

}
