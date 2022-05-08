package com.zara.price.domain.service;

import com.zara.price.application.request.PriceRequest;
import com.zara.price.domain.Price;

public interface PriceService {

	Price findPriceByDateAndProductAndBrand(PriceRequest priceRequest);

}