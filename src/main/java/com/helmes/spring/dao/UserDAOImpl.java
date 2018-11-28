package com.helmes.spring.dao;

import com.helmes.spring.model.Role;
import com.helmes.spring.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);


    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override

    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        ArrayList<Role> roles = new ArrayList<Role>(user.getRoles());
        user.setRoles(roles);
        session.persist(user);
        //session.save(user);  //persist
        logger.info("User saved successfully, User Details="+user);
    }
    @Override

    public User findByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        User u = (User) query.uniqueResult();
        logger.info("User loaded successfully, User details="+u);
        return u;
    }
}
