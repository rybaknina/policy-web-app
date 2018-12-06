package com.helmes.spring.service;

import java.math.BigDecimal;
import java.util.List;

import com.helmes.spring.model.Policy;
import com.helmes.spring.util.PaginationResult;

public interface PolicyService {

    public void addPolicy(Policy p);
    public void updatePolicy(Policy p);
    public PaginationResult<Policy> listPolicys(int page, int maxResult, int maxNavigationPage);
    public Policy getPolicyById(int id);
    public void removePolicy(int id);
    public List<Policy> findPolicys(BigDecimal pricef, String typef, Boolean activef);

}