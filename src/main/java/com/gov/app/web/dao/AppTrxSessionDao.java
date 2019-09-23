package com.gov.app.web.dao;

import com.gov.app.web.model.AppTrxSession;

import java.math.BigDecimal;
import java.util.List;

public interface AppTrxSessionDao {

    AppTrxSession findById(BigDecimal id);

    BigDecimal saveAppTrxSession(AppTrxSession entity);

    public void saveOrUpdate(AppTrxSession entity);

    Boolean deleteAppTrxSessionByuserId(int userId);
    
    Boolean cekSession(int userId);

    List<AppTrxSession> findAllAppTrxSessions();

    List<AppTrxSession> findAppTrxSessionByUserId(int UserId);

}
