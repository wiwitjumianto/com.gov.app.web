package com.gov.app.web.daoimpl;

import com.gov.app.web.dao.AbstractDao;
import com.gov.app.web.dao.AppMstProposerDao;
import com.gov.app.web.model.AppMstProposer;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("proposer")
public class AppMstProposerDaoImpl extends AbstractDao<Integer, AppMstProposer> implements AppMstProposerDao {

    @Override
    @Transactional
    public AppMstProposer findById(int id) {
        return getByKey(id);
    }

    @Override
    @Transactional
    public void saveAppMstProposer(AppMstProposer proposer) {
        persist(proposer);
    }

    @Override
    @Transactional
    public void saveOrUpdate(AppMstProposer proposer) {
        super.saveOrUpdate(proposer);
    }

    @Override
    @Transactional
    public void deleteAppMstProposerByCode(String code) {
        Query query = getSession().createSQLQuery("delete from AppMstProposer where AppMstProposerId = :code");
        query.setString("code", code);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<AppMstProposer> findAllAppMstProposers() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("deletedStatus", 0));
        return (List<AppMstProposer>) criteria.list();
    }

    @Override
    public AppMstProposer findAppMstProposerByCode(String code) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("code", code));
        return (AppMstProposer) criteria.uniqueResult();
    }

}
