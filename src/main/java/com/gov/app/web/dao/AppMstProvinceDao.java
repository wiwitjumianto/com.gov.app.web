package com.gov.app.web.dao;

import com.gov.app.web.model.AppMstProvince;
import java.util.Collection;
import java.util.List;

public interface AppMstProvinceDao {

    AppMstProvince findById(int id);

    void saveAppMstProvince(AppMstProvince student);

    public void saveOrUpdate(AppMstProvince student);

    void deleteAppMstProvinceByCode(String ssn);

    List<AppMstProvince> findAllAppMstProvinces();

    AppMstProvince findAppMstProvinceByCode(String ssn);

}
