package com.gov.app.web.dao;

import com.gov.app.web.model.AppMstProposer;
import java.util.List;

public interface AppMstProposerDao {

    AppMstProposer findById(int id);

    void saveAppMstProposer(AppMstProposer proposer);

    public void saveOrUpdate(AppMstProposer proposer);

    void deleteAppMstProposerByCode(String code);

    List<AppMstProposer> findAllAppMstProposers();

    AppMstProposer findAppMstProposerByCode(String code);

}
