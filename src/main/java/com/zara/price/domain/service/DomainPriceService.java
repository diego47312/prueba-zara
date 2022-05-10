package com.zara.price.domain.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.zara.price.domain.Price;
import com.zara.price.domain.ResourceNotFoundException;
import com.zara.price.domain.repository.PriceRepository;


public class DomainPriceService implements PriceService {

	private final PriceRepository priceRepository;
	
	public DomainPriceService(final PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price findPriceByDateAndProductAndBrand(Long brandId, Long productId, Date date) {

		List<Price> prices = priceRepository.findByBrandIdAndProductIdAndDate(
				brandId, 
				productId, 
				date);
		
		// Si hay mas de un precio para la misma fecha se obtiene el de maxima prioridad
		return prices
			      .stream()
			      .max(Comparator.comparing(Price::getPriority))
			      .orElseThrow(() -> new ResourceNotFoundException("No hay precio disponible"));
	}

}
