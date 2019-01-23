package com.helmes.spring.service;

import com.helmes.spring.model.Buy;
import com.helmes.spring.util.PaginationResult;

import java.util.List;
import java.util.UUID;

public interface BuyService {
    void saveBuy(Buy buy);
    void changeBuy(Buy buy);
    PaginationResult<Buy> listBuy(int page, int maxResult, int maxNavigationPage);
    Buy getBuyById(UUID id);
    void cancelBuy(UUID id);
}
