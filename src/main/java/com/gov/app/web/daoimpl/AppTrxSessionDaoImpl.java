package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppTrxSessionDao;
import com.gov.app.web.model.AppTrxSession;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("session")
public class AppTrxSessionDaoImpl extends AbstractDao<BigDecimal, AppTrxSession> implements AppTrxSessionDao {

    @Override
    @Transactional
    public AppTrxSession findById(BigDecimal id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public BigDecimal saveAppTrxSession(AppTrxSession session) {
        return create(session);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppTrxSession province) {
        super.saveOrUpdate(province);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppTrxSession> findAllAppTrxSessions() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<AppTrxSession>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppTrxSession> findAppTrxSessionByUserId(int userId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        criteria.add(Restrictions.and(Restrictions.eq("appTrxUserId", userId)));
        return (List<AppTrxSession>) criteria.list();
    }

    @Override
    @Transactional
    public Boolean cekSession(int userId) {
        Query query = getSession().createSQLQuery("select * from AppTrxSession where appTrxUserId =:userId")
                .setParameter("userId", userId);
        List data = query.list();
        if (data.size() <= 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteAppTrxSessionByuserId(int userId) {
        try {
            Query query = getSession().createSQLQuery("delete from App_Trx_Session where App_Trx_User_Id=:userId");
            query.setParameter("userId", userId);
            query.executeUpdate();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
