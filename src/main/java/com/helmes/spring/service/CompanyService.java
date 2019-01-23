package com.helmes.spring.service;

import com.helmes.spring.model.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(UUID id);
}
