package com.helmes.spring.dao;

import java.math.BigDecimal;
import java.util.List;

import com.helmes.spring.model.Policy;
import com.helmes.spring.util.PaginationResult;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PolicyDAOImpl implements PolicyDAO {

    private static final Logger logger = LoggerFactory.getLogger(PolicyDAOImpl.class);
    private static final int limitResultsPerPage = 5;
    @Autowired
    private SessionFactory sessionFactory;
    private String sqllist = "from Policy where is_delete =false or is_delete is null";
    private String sqlfindlist = "from Policy where (is_delete =false or is_delete is null) and price >= :pricef and type = :typef and is_active = :activef";
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
//    @Transactional
    public void addPolicy(Policy p) {
        Session session = this.sessionFactory.getCurrentSession();

        session.persist(p);
        logger.info("Policy saved successfully, Policy Details="+p);
    }

    @Override
//    @Transactional
    public void updatePolicy(Policy p) {
        Session session = this.sessionFactory.getCurrentSession();
        //TODO looks strange. If you need to update some data - you need to get updated entity, set new data in it, and .merge()

        Query query = session.createQuery("update Policy set price = :price, id_type = :type, is_active = :active where id = :id");
        query.setParameter("id", p.getId());
        query.setParameter("price", (BigDecimal) p.getPrice());
        query.setParameter("active",  p.getActive());
        query.setParameter("type", p.getType());
   //     query.setParameter("typename", p.getTypename());
        query.executeUpdate();
      //  session.update(p);
        logger.info("Policy updated successfully, Policy Details="+p);
    }

    @SuppressWarnings("unchecked")
    @Override
//    @Transactional(readOnly = true)
    public PaginationResult<Policy> listPolicys(int page, int maxResult, int maxNavigationPage) {
        Session session = this.sessionFactory.getCurrentSession();

        Query query = session.createQuery(sqllist);
    //    query.setFirstResult((page -1)* limitResultsPerPage);
    //    query.setMaxResults(limitResultsPerPage);
    //    List<Policy> policysList = query.list();
    //    for(Policy p : policysList){
    //        logger.info("Policy List::"+p);
    //    }

        return new PaginationResult<Policy>(query, page, maxResult, maxNavigationPage);
    }


    @Override
//    @Transactional(readOnly = true)
    public Policy getPolicyById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Policy where id = :id ");
        query.setParameter("id", id);
        Policy p = (Policy) query.uniqueResult();
 //       query.setParameter("typename", p.getTypename());

        //  Policy p = (Policy) session.load(Policy.class, new Integer(id));
        logger.info("Policy loaded successfully, Policy details="+p);
        return p;
    }

    @Override
//    @Transactional
    public void removePolicy(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Policy set is_delete = true, is_active = false where id = :id ");
        query.setParameter("id", id);
        int result = query.executeUpdate();
  //      Policy p = (Policy) session.load(Policy.class, new Integer(id));
  //      if(null != p){
  //          session.delete(p);
  //      }
        logger.info("Policy deleted successfully, policy details="+id);
    }

    @Override
 //   @Transactional(readOnly = true)
    public List<Policy> findPolicys(BigDecimal pricef, String typef, Boolean activef) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sqlfindlist);

        query.setParameter("pricef", pricef);
        query.setParameter("typef", typef);
        query.setParameter("activef", activef);

        List<Policy> policysList = query.list();

        for(Policy p : policysList){
            logger.info("Policy List::"+p);
        }
        return policysList;
    }

}