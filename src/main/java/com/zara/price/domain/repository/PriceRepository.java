package com.zara.price.domain.repository;

import java.util.Date;
import java.util.List;

import com.zara.price.domain.Price;

public interface PriceRepository {

	List<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, Date date);

}
