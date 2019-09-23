package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.GovTrxProgrammDetailDao;
import com.gov.app.web.dao.GovTrxProgrammHeaderDao;
import com.gov.app.web.model.GovTrxProgrammDetail;
import com.gov.app.web.model.GovTrxProgrammHeader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("programmheader")
public class GovTrxProgrammHeaderDaoImpl extends AbstractDao<Integer, GovTrxProgrammHeader> implements GovTrxProgrammHeaderDao {

    @Autowired
    private GovTrxProgrammDetailDao daoDet;

    @Override
    @Transactional
    public GovTrxProgrammHeader findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveGovTrxProgrammHeader(GovTrxProgrammHeader villageparam) {
        persist(villageparam);
    }

    @Override
    @Transactional
    public void saveOrUpdate(GovTrxProgrammHeader villageparam) {
        super.saveOrUpdate(villageparam);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<GovTrxProgrammHeader> findAllGovTrxProgrammHeaders() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<GovTrxProgrammHeader>) criteria.list();
    }

    @Override
    @Transactional
    public GovTrxProgrammHeader findGovTrxProgrammHeaderActive() {
        Query query = getSession().createSQLQuery("SELECT gov_trx_programm_header_id from gov_trx_programm_header p "
                + "WHERE NOW() BETWEEN p.gov_trx_programm_header_start_date AND p.gov_trx_programm_header_end_date "
                + "and p.status=1 and p.deleted_Status=0");
        List data = query.list();
        GovTrxProgrammHeader dataEnt = new GovTrxProgrammHeader();
        GovTrxProgrammHeader dataReturn = new GovTrxProgrammHeader();
        for (int i = 0; i < data.size(); i++) {
            dataEnt.setGovTrxProgrammHeaderId(Integer.parseInt(data.get(i).toString()));
        }
        if (dataEnt.getGovTrxProgrammHeaderId() != null) {
            dataReturn = findById(dataEnt.getGovTrxProgrammHeaderId());
        }

        return dataReturn;
    }

    @Override
    @Transactional
    public Boolean saveHeaderandDetail(GovTrxProgrammHeader header, List<GovTrxProgrammDetail> listDetail) {
        try {
            int headerId = create(header);
            for (int i = 0; i < listDetail.size(); i++) {
                listDetail.get(i).setGovTrxProgrammHeaderId(headerId);
                daoDet.saveGovTrxProgrammDetail(listDetail.get(i));
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean updateHeaderandDetail(GovTrxProgrammHeader header, List<GovTrxProgrammDetail> listDetail) {
        try {
            saveOrUpdate(header);
            daoDet.deleteGovTrxProgrammDetailByHeaderId(header.getGovTrxProgrammHeaderId());
            for (int i = 0; i < listDetail.size(); i++) {
                listDetail.get(i).setGovTrxProgrammHeaderId(header.getGovTrxProgrammHeaderId());
                daoDet.saveGovTrxProgrammDetail(listDetail.get(i));
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean deleteGovTrxProgrammHeader(GovTrxProgrammHeader ProgHeader) {
        try {
            saveOrUpdate(ProgHeader);
            daoDet.deleteGovTrxProgrammDetailByHeaderId(ProgHeader.getGovTrxProgrammHeaderId());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean runningGovTrxProgrammHeader(GovTrxProgrammHeader id) {
        try {
            Query query = getSession().createSQLQuery("update Gov_Trx_Programm_header g set g.status=0");
            query.executeUpdate();
            saveOrUpdate(id);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

}
