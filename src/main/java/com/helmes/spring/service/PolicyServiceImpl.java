package com.helmes.spring.service;

import java.math.BigDecimal;
import java.util.List;

import com.helmes.spring.dao.PolicyDAO;
import com.helmes.spring.model.Policy;
import com.helmes.spring.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PolicyServiceImpl implements PolicyService {
    @Autowired
    private PolicyDAO policyDAO;

    public void setPolicyDAO(PolicyDAO policyDAO) {
        this.policyDAO = policyDAO;
    }

    @Override
    @Transactional
    public void addPolicy(Policy p) {
        this.policyDAO.addPolicy(p);
    }

    @Override
    @Transactional
    public void updatePolicy(Policy p) {
        this.policyDAO.updatePolicy(p);
    }

    @Override
    @Transactional
    public PaginationResult<Policy> listPolicys(int page, int maxResult, int maxNavigationPage) {
        return this.policyDAO.listPolicys(page, maxResult, maxNavigationPage);
    }

    @Override
    @Transactional
    public Policy getPolicyById(int id) {
        return this.policyDAO.getPolicyById(id);
    }

    @Override
    @Transactional
    public void removePolicy(int id) {
        this.policyDAO.removePolicy(id);
    }
    @Override
    @Transactional
    public List<Policy> findPolicys(BigDecimal pricef, String typef, Boolean activef) {
        return this.policyDAO.findPolicys(pricef, typef, activef);
    }

}