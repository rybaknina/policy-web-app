package com.helmes.spring.dao;

import com.helmes.spring.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyDAO {
    Company getCompanyById(UUID id);
    List<Company> getAllCompanies();
}
