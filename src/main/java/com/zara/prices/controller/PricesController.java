package com.zara.prices.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zara.prices.dto.PriceResponseDto;
import com.zara.prices.dto.PriceRequestDto;
import com.zara.prices.service.PricesService;

@RestController
@RequestMapping(value = "/api/prices")
public class PricesController {

	@Autowired
	private PricesService pricesService;

	@PostMapping("/findPrices")
	public ResponseEntity<PriceResponseDto> findPrices(@Valid @RequestBody PriceRequestDto priceRequestDto) {
		return new ResponseEntity<>(pricesService.findPrices(priceRequestDto), HttpStatus.FOUND);
	}

}
