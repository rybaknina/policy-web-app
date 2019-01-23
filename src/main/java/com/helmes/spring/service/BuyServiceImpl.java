package com.helmes.spring.service;

import com.helmes.spring.dao.BuyDAO;
import com.helmes.spring.model.Buy;
import com.helmes.spring.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    BuyDAO buyDAO;

    public void setBuyDAO(BuyDAO buyDAO) {
        this.buyDAO = buyDAO;
    }

    @Override
    @Transactional
    public void saveBuy(Buy buy) {
        buyDAO.saveBuy(buy);
    }

    @Override
    @Transactional
    public void changeBuy(Buy buy) {
        buyDAO.saveBuy(buy);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginationResult<Buy> listBuy(int page, int maxResult, int maxNavigationPage) {
        return buyDAO.listBuy(page, maxResult, maxNavigationPage);
    }

    @Override
    @Transactional(readOnly = true)
    public Buy getBuyById(UUID id) {
        return buyDAO.getBuyById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public void cancelBuy(UUID id) {
        buyDAO.cancelBuy(id);
    }
}
