package com.zara.price.application.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zara.price.application.request.PriceRequest;
import com.zara.price.application.response.PriceResponse;
import com.zara.price.domain.Price;
import com.zara.price.domain.PriceMapper;
import com.zara.price.domain.service.PriceService;

@RestController
@RequestMapping(value = "/api/prices")
public class PriceController {

	private final PriceService priceService;
	private final PriceMapper priceMapper;

	@Autowired
	public PriceController(PriceService priceService, PriceMapper priceMapper) {
		this.priceService = priceService;
		this.priceMapper = priceMapper;
	}

	@GetMapping
	public ResponseEntity<PriceResponse> findPrices(@Valid PriceRequest priceRequest) {
		Price price = priceService.findPriceByDateAndProductAndBrand(
				priceRequest.getBrandId(), 
				priceRequest.getProductId(), 
				priceRequest.getDate());
		PriceResponse priceResponse = priceMapper.priceToResponsePrice(price);
		return new ResponseEntity<>(priceResponse, HttpStatus.FOUND);
	}

}
