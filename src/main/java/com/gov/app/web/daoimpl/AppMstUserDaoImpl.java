package com.gov.app.web.daoimpl;

import com.gov.app.web.config.MyHibernateConfig;
import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppMstUserDao;
import com.gov.app.web.dao.AppTrxSessionDao;
import com.gov.app.web.model.AppMstUser;
import com.gov.app.web.model.AppTrxSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("user")
public class AppMstUserDaoImpl extends AbstractDao<Integer, AppMstUser> implements AppMstUserDao {

    @Override
    @Transactional
    public AppMstUser findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstUser(AppMstUser user) {
        persist(user);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppMstUser user) {
        super.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void deleteAppMstUserByCode(String code) {
        Query query = getSession().createSQLQuery("delete from AppMstUser where code = :code");
        query.setString("code", code);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstUser> findAllAppMstUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<AppMstUser>) criteria.list();
    }

    @Override
    public AppMstUser findAppMstUserByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (AppMstUser) criteria.uniqueResult();
    }

    @Autowired
    private AppTrxSessionDao daoSession;

    @Override
    @Transactional
    public int login(String uname, String pwd) {

        BigDecimal sessionId = BigDecimal.ZERO;
        StringBuilder hql = new StringBuilder();
        hql.append("FROM AppMstUser ");
        hql.append("WHERE appMstUserName =:uname and appMstUserPassword = :pwd and deletedStatus = 0 and status = 1");
        Query query = getSession().createQuery(hql.toString());
        query.setParameter("uname", uname);
        query.setParameter("pwd", pwd);
//        Query query
//                = getSession().createQuery("select d from AppMstUser d "
//                        + "where d.appMstUserName=:uname and d.appMstUserPassword=:pwd ")
//                        .setParameter("uname", uname).setParameter("pwd", pwd);
        BigDecimal userId = BigDecimal.ZERO;
        try {

            List<AppMstUser> data = new ArrayList<>();
            data = query.list();
            if (data.size() > 0) {
                AppTrxSession dataSssion = new AppTrxSession();

                for (int i = 0; i < data.size(); i++) {
                    userId = data.get(i).getAppMstUserId();
                }
                dataSssion.setAppTrxUserId(userId);
                dataSssion.setDeletedStatus(BigDecimal.ZERO);
                dataSssion.setAppTrxDateCreated(new Date());
                dataSssion.setAppTrxDateDeleted(new Date());
                sessionId = daoSession.saveAppTrxSession(dataSssion);
            }
        } catch (Exception e) {
            return 1;
        }
        int userIdd = Integer.parseInt (String.valueOf(userId));

        return userIdd;
    }
}
