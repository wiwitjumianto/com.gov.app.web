package com.gov.app.web.dao;

import com.gov.app.web.model.GovTrxRealization;
import java.util.List;

public interface GovTrxRealizationDao {

    GovTrxRealization findById(int id);

    void saveGovTrxRealization(GovTrxRealization realization);

    public void saveOrUpdate(GovTrxRealization realization);

    void deleteGovTrxRealizationByHeaderId(int headerId);

    List<GovTrxRealization> findAllGovTrxRealizations();

    GovTrxRealization findGovTrxRealizationByCode(String ssn);

    public List<GovTrxRealization> findAllGovTrxRealizationsByHeader(int headerId);

    public List<GovTrxRealization> findAllGovTrxRealizationsByVillage(int villageId, int headerId);

    public List<GovTrxRealization> findAllGovTrxRealizationsByVillageList(List<Integer> listVillageId, int headerId);

    List<GovTrxRealization> findAllGovTrxRealizationsByNIK(String NIK, int headerId);

}
