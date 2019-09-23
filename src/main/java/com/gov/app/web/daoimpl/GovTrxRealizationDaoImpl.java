package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.GovTrxRealizationDao;
import com.gov.app.web.model.GovTrxRealization;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("realization")
public class GovTrxRealizationDaoImpl extends AbstractDao<Integer, GovTrxRealization> implements GovTrxRealizationDao {

    @Override
    @Transactional
    public GovTrxRealization findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveGovTrxRealization(GovTrxRealization realization) {
        persist(realization);
    }

    @Override
    @Transactional
    public void saveOrUpdate(GovTrxRealization realization) {
        super.saveOrUpdate(realization);
    }

    @Override
    @Transactional
    public void deleteGovTrxRealizationByHeaderId(int headerId) {
        Query query = getSession().createSQLQuery("update Gov_Trx_Programm_Detail g set g.deleted_Status=1 where g.gov_Trx_Programm_Header_Id=:headerId");
        query.setParameter("headerId", headerId);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<GovTrxRealization> findAllGovTrxRealizations() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<GovTrxRealization>) criteria.list();
    }

    @Override
    public GovTrxRealization findGovTrxRealizationByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (GovTrxRealization) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public List<GovTrxRealization> findAllGovTrxRealizationsByHeader(int headerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("govTrxProgrammHeaderId", headerId));
        criteria.add(Restrictions.and(Restrictions.eq("deletedStatus", 0)));
        return (List<GovTrxRealization>) criteria.list();
    }

    @Override
    @Transactional
    public List<GovTrxRealization> findAllGovTrxRealizationsByVillage(int villageId, int headerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("govTrxRealizationVillageId", villageId));
        criteria.add(Restrictions.and(Restrictions.eq("deletedStatus", 0)));
        criteria.add(Restrictions.and(Restrictions.eq("govTrxProgrammHeaderId", headerId)));
        return (List<GovTrxRealization>) criteria.list();
    }

    @Override
    @Transactional
    public List<GovTrxRealization> findAllGovTrxRealizationsByVillageList(List<Integer> listVillageId, int headerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("govTrxProgrammHeaderId", headerId));
        criteria.add(Restrictions.and(Restrictions.eq("deletedStatus", 0)));
        criteria.add(Restrictions.and(Restrictions.in("govTrxRealizationVillageId", listVillageId)));
        return (List<GovTrxRealization>) criteria.list();
    }

    @Override
    @Transactional
    public List<GovTrxRealization> findAllGovTrxRealizationsByNIK(String NIK, int headerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("govTrxProgrammHeaderId", headerId));
        criteria.add(Restrictions.and(Restrictions.eq("deletedStatus", 0)));
        criteria.add(Restrictions.and(Restrictions.eq("govTrxRealizationReceiverNik", NIK)));
        return (List<GovTrxRealization>) criteria.list();
    }

}
