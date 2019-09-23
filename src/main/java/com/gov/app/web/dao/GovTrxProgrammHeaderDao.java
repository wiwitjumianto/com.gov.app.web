package com.gov.app.web.dao;

import com.gov.app.web.model.GovTrxProgrammDetail;
import com.gov.app.web.model.GovTrxProgrammHeader;
import java.util.List;

public interface GovTrxProgrammHeaderDao {

    GovTrxProgrammHeader findById(int id);

    void saveGovTrxProgrammHeader(GovTrxProgrammHeader student);

    public void saveOrUpdate(GovTrxProgrammHeader student);

    public Boolean deleteGovTrxProgrammHeader(GovTrxProgrammHeader id);
    
    public Boolean runningGovTrxProgrammHeader(GovTrxProgrammHeader id);

    List<GovTrxProgrammHeader> findAllGovTrxProgrammHeaders();

    GovTrxProgrammHeader findGovTrxProgrammHeaderActive();

    public Boolean saveHeaderandDetail(GovTrxProgrammHeader header, List<GovTrxProgrammDetail> listDetail);
    
    public Boolean updateHeaderandDetail(GovTrxProgrammHeader header, List<GovTrxProgrammDetail> listDetail);
    
    

}
