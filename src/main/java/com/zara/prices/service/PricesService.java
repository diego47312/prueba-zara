package com.zara.prices.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zara.prices.dto.PriceResponseDto;
import com.zara.prices.dto.PriceRequestDto;
import com.zara.prices.exception.ResourceNotFoundException;
import com.zara.prices.mapper.PriceMapper;
import com.zara.prices.model.Price;
import com.zara.prices.repository.PricesRepository;

@Service
public class PricesService {

	@Autowired
	private PricesRepository pricesRepository;

	@Autowired
	private PriceMapper priceMapper;

	public PriceResponseDto findPrices(PriceRequestDto priceRequestDto) {

		List<Price> prices = pricesRepository.findByBrandIdAndProductIdAndDate(
				priceRequestDto.getBrandId(), 
				priceRequestDto.getProductId(), 
				priceRequestDto.getDate());
		
		Price price = prices
			      .stream()
			      .max(Comparator.comparing(Price::getPriority))
			      .orElseThrow(() -> new ResourceNotFoundException("No hay precio disponible"));
		
		return priceMapper.priceToResponsePriceDto(price);
	}

}
