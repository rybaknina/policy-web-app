package com.helmes.spring.dao;

import com.helmes.spring.model.Company;
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
public class CompanyDAOImpl implements CompanyDAO {
    private static final Logger logger = LoggerFactory.getLogger(CompanyDAOImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    @Override
    public Company getCompanyById(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Company where id = :id");
        query.setParameter("id", id);
        Company company = (Company)query.uniqueResult();
        logger.info("Company loaded successfully, Company details="+company);
        return company;
    }

    @Override
    public List<Company> getAllCompanies() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Company");
        List<Company> companyList = query.list();

        for(Company t : companyList){
            logger.info("Company List::"+t);
        }
        return companyList;
    }
}
