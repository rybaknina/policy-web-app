package com.helmes.spring.dao;

import com.helmes.spring.model.Buy;
import com.helmes.spring.util.PaginationResult;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public class BuyDAOImpl implements BuyDAO {
    private static final String SQL_GET_ALL = "from Buy where is_delete =false or is_delete is null";
    private static final String SQL_GET_ID = "from Buy where id = :id ";

    private static final String SQL_CANL = "update Buy set is_delete = true, is_active = false where id = :id ";

    private static final Logger logger = LoggerFactory.getLogger(BuyDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveBuy(Buy buy) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(buy);
        logger.info("Buy saved successfully, Buy Details="+buy);
    }

    @Override
    public void changeBuy(Buy buy) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(buy);
        logger.info("Buy changed successfully, Buy Details="+buy);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PaginationResult<Buy> listBuy(int page, int maxResult, int maxNavigationPage) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(SQL_GET_ALL);
        return new PaginationResult<Buy>(query, page, maxResult, maxNavigationPage);
    }

    @Override
    public Buy getBuyById(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(SQL_GET_ID);
        query.setParameter("id", id);
        Buy buy = (Buy)query.uniqueResult();
        logger.info("Buy loaded successfully, Buy details="+buy);
        return buy;
    }

    @Override
    public void cancelBuy(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(SQL_CANL);
        query.setParameter("id", id);
        int result = query.executeUpdate();
        logger.info("Buy canceled successfully, Buy details="+id);
    }
}
