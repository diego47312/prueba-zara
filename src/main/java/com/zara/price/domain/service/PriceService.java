package com.zara.price.domain.service;

import java.util.Date;

import com.zara.price.domain.Price;

public interface PriceService {

	Price findPriceByDateAndProductAndBrand(Long brandId, Long productId, Date date);

}