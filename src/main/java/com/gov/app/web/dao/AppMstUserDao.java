package com.gov.app.web.dao;

import com.gov.app.web.model.AppMstUser;
import java.util.List;

public interface AppMstUserDao {

    AppMstUser findById(int id);

    public int login(String uname, String pwd);

    void saveAppMstUser(AppMstUser student);

    public void saveOrUpdate(AppMstUser student);

    void deleteAppMstUserByCode(String ssn);

    List<AppMstUser> findAllAppMstUsers();

    AppMstUser findAppMstUserByCode(String ssn);

}
