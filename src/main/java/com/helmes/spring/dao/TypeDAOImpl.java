package com.helmes.spring.dao;

import com.helmes.spring.model.Type;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TypeDAOImpl implements TypeDAO {

    private static final Logger logger = LoggerFactory.getLogger(TypeDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    @Transactional
    public Type getTypeById(String id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Type where id = :id ");
        query.setParameter("id", id);
        Type t = (Type) query.uniqueResult();
        logger.info("Type loaded successfully, Type details="+t);
        return t;
    }
    @Override
    @Transactional
    public List<Type> getAllTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Type");
        List<Type> typeList = query.list();

        for(Type t : typeList){
            logger.info("Type List::"+t);
        }
        return typeList;
    }
    @Override
    @Transactional
    public Map<String, String> getMapTypes(){
        Map<String, String> mapTypes = new HashMap<String, String>();
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Type");
        List<Type> typeList = query.list();

        for(Type t : typeList){
            mapTypes.put(t.getId(), t.getType());
        }
        return mapTypes;
    }

}