package com.zara.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zara.prices.dto.PriceRequestDto;
import com.zara.prices.dto.PriceResponseDto;
import com.zara.prices.exception.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PricesControllerIntegrationTest {

	@Autowired
	PricesController priceController;

	@Test
	void testGetPriceAt20200614T10() {

		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(formatStringToDate("2020-06-14T10:00:00"));

		PriceResponseDto priceResponseDto = priceController.findPrices(priceRequestDto).getBody();

		assertEquals("35.50", priceResponseDto.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponseDto.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponseDto.getProductId());
		assertEquals(formatStringToDate("2020-06-14T00:00:00"), priceResponseDto.getStartDate());
		assertEquals(formatStringToDate("2020-12-31T23:59:59"), priceResponseDto.getEndDate());

	}

	@Test
	void testGetPriceAt20200614T16() {

		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(formatStringToDate("2020-06-14T16:00:00"));

		PriceResponseDto priceResponseDto = priceController.findPrices(priceRequestDto).getBody();

		assertEquals("25.45", priceResponseDto.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponseDto.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponseDto.getProductId());
		assertEquals(formatStringToDate("2020-06-14T15:00:00"), priceResponseDto.getStartDate());
		assertEquals(formatStringToDate("2020-06-14T18:30:00"), priceResponseDto.getEndDate());

	}

	@Test
	void testGetPriceAt20200614T21() {

		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(formatStringToDate("2020-06-14T21:00:00"));

		PriceResponseDto priceResponseDto = priceController.findPrices(priceRequestDto).getBody();

		assertEquals("35.50", priceResponseDto.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponseDto.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponseDto.getProductId());
		assertEquals(formatStringToDate("2020-06-14T00:00:00"), priceResponseDto.getStartDate());
		assertEquals(formatStringToDate("2020-12-31T23:59:59"), priceResponseDto.getEndDate());

	}

	@Test
	void testGetPriceAt20200615T10() {

		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(formatStringToDate("2020-06-15T10:00:00"));

		PriceResponseDto priceResponseDto = priceController.findPrices(priceRequestDto).getBody();

		assertEquals("30.50", priceResponseDto.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponseDto.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponseDto.getProductId());
		assertEquals(formatStringToDate("2020-06-15T00:00:00"), priceResponseDto.getStartDate());
		assertEquals(formatStringToDate("2020-06-15T11:00:00"), priceResponseDto.getEndDate());

	}

	@Test
	void testGetPriceAt20200616T21() {

		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(formatStringToDate("2020-06-16T21:00:00"));

		PriceResponseDto priceResponseDto = priceController.findPrices(priceRequestDto).getBody();

		assertEquals("38.95", priceResponseDto.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponseDto.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponseDto.getProductId());
		assertEquals(formatStringToDate("2020-06-15T16:00:00"), priceResponseDto.getStartDate());
		assertEquals(formatStringToDate("2020-12-31T23:59:59"), priceResponseDto.getEndDate());

	}

	@Test
	void testGetPriceResourceNotFoundException() {

		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(formatStringToDate("2022-06-16T21:00:00"));

		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> priceController.findPrices(priceRequestDto));

		assertEquals("No hay precio disponible", exception.getMessage());

	}

	private Date formatStringToDate(String dateString) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		try {
			return simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

}
