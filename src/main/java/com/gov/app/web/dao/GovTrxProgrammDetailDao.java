package com.gov.app.web.dao;

import com.gov.app.web.model.GovTrxProgrammDetail;
import java.util.List;
import java.util.Map;

public interface GovTrxProgrammDetailDao {

    GovTrxProgrammDetail findById(int id);

    void saveGovTrxProgrammDetail(GovTrxProgrammDetail student);

    public void saveOrUpdate(GovTrxProgrammDetail student);

    void deleteGovTrxProgrammDetailByHeaderId(int headerId);

    List<GovTrxProgrammDetail> findAllGovTrxProgrammDetails();

    GovTrxProgrammDetail findGovTrxProgrammDetailByCode(String ssn);

    GovTrxProgrammDetail findGovTrxProgrammDetailByvillage(int villageId, int headerId);

    public List<GovTrxProgrammDetail> findAllGovTrxProgrammDetailsByHeader(int headerId);

    public Map<String, Object> findAllRealizationGroupByProvince(int headerId);

}
