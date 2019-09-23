package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.GovTrxProgrammDetailDao;
import com.gov.app.web.model.GovTrxProgrammDetail;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("programmdetail")
public class GovTrxProgrammDetailDaoImpl extends AbstractDao<Integer, GovTrxProgrammDetail> implements GovTrxProgrammDetailDao {

    @Override
    @Transactional
    public GovTrxProgrammDetail findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveGovTrxProgrammDetail(GovTrxProgrammDetail villageparam) {
        persist(villageparam);
    }

    @Override
    @Transactional
    public void saveOrUpdate(GovTrxProgrammDetail villageparam) {
        super.saveOrUpdate(villageparam);
    }

    @Override
    @Transactional
    public void deleteGovTrxProgrammDetailByHeaderId(int headerId) {
        Query query = getSession().createSQLQuery("update Gov_Trx_Programm_Detail g set g.deleted_Status=1 where g.gov_Trx_Programm_Header_Id=:headerId");
        query.setParameter("headerId", headerId);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<GovTrxProgrammDetail> findAllGovTrxProgrammDetails() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.eq("govTrxProgrammHeaderId", getHreaderId()));
        return (List<GovTrxProgrammDetail>) criteria.list();

    }

    public int getHreaderId() {
        int result = 0;
        Query query = getSession().createSQLQuery("SELECT p.GOV_TRX_PROGRAMM_HEADER_ID from gov_trx_programm_header p "
                + "WHERE NOW() BETWEEN p.gov_trx_programm_header_start_date AND p.gov_trx_programm_header_end_date "
                + "and p.status=1 and p.deleted_Status=0");
        List data = new ArrayList<>();;
        data = query.list();
        for (int i = 0; i < data.size(); i++) {
            result = (int) data.get(i);
        }
        return result;
    }

    @Override
    public GovTrxProgrammDetail findGovTrxProgrammDetailByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (GovTrxProgrammDetail) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public List<GovTrxProgrammDetail> findAllGovTrxProgrammDetailsByHeader(int headerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("govTrxProgrammHeaderId", headerId));
        criteria.add(Restrictions.and(Restrictions.eq("deletedStatus", 0)));
        return (List<GovTrxProgrammDetail>) criteria.list();
    }

    @Override
    @Transactional
    public Map<String, Object> findAllRealizationGroupByProvince(int headerId) {
        Map<String, Object> result = new HashMap();
        List<Map> resultMap = new ArrayList();
        Query query = getSession().createSQLQuery("SELECT v.APP_MST_PROVINCE_ID as province_id, SUM(r.GOV_TRX_PROGRAMM_UNIT) as sum_province"
                + " FROM gov_trx_programm_detail r inner join app_mst_village v "
                + "on v.APP_MST_VILLAGE_ID = r.GOV_TRX_PROGRAMM_VILLAGE_ID "
                + "WHERE r.GOV_TRX_PROGRAMM_HEADER_ID =:headerId and r.DELETED_STATUS=0 "
                + "GROUP by v.APP_MST_PROVINCE_ID").setParameter("headerId", headerId);

        List<BigDecimal> dataResult = query.list();
        List dataTp = new ArrayList();
        Object dataObj = new Object();
        for (int i = 0; i < dataResult.size(); i++) {
            for (Iterator iterator = dataResult.iterator(); iterator.hasNext();) {
                Object next = iterator.next();
                System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>.. " + next.toString());
            }

//            for (int j = 0; j < 1; j++) {
//                result.put("provinceId", dataTp.get(j));
//                result.put("sunUnit", dataTp.get(j + 1));
//                resultMap.add(result);
//            }
            result = new HashMap();
        }
        result.put("data", resultMap);
        return result;
    }

    @Override
    @Transactional
    public GovTrxProgrammDetail findGovTrxProgrammDetailByvillage(int villageId, int headerId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("govTrxProgrammHeaderId", headerId));
        criteria.add(Restrictions.and(Restrictions.eq("deletedStatus", 0)));
        criteria.add(Restrictions.and(Restrictions.eq("govTrxProgrammVillageId", villageId)));
        List<GovTrxProgrammDetail> dataList = criteria.list();
        GovTrxProgrammDetail result = new GovTrxProgrammDetail();
        for (int i = 0; i < dataList.size(); i++) {
            result = dataList.get(i);
        }
        return result;
    }
}
