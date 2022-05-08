package com.zara.price.infraestructure.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zara.price.domain.Price;
import com.zara.price.domain.repository.PriceRepository;

@Component
public class JpaDbPricesRepository implements PriceRepository {

	private final SpringDataJpaDbPriceRepository pricesRepository;
	private final PriceEntityMapper priceEntityMapper;

	@Autowired
	public JpaDbPricesRepository(final SpringDataJpaDbPriceRepository pricesRepository, final PriceEntityMapper priceEntityMapper) {
		this.pricesRepository = pricesRepository;
		this.priceEntityMapper = priceEntityMapper;
	}

	@Override
	public List<Price> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, Date date) {
		List<PriceEntity> prices = pricesRepository.findByBrandIdAndProductIdAndDate(brandId, productId, date);
		return priceEntityMapper.pricesEntityToPrices(prices);
	}

}
