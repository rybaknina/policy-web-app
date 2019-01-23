package com.helmes.spring.service;

import com.helmes.spring.dao.CompanyDAO;
import com.helmes.spring.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyDAO companyDAO;

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Company> getAllCompanies() {
        return this.companyDAO.getAllCompanies();
    }

    @Override
    @Transactional(readOnly = true)
    public Company getCompanyById(UUID id) {
        return this.companyDAO.getCompanyById(id);
    }
}
