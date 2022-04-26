package com.zara.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zara.prices.dto.PriceResponseDto;
import com.zara.prices.dto.PriceRequestDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PricesControllerIntegrationTest {

	@Autowired
	PricesController priceController;

	@Test
	void test1() {

		testPrices("2020-06-14T10:00:00", "35.50");

	}

	@Test
	void test2() {

		testPrices("2020-06-14T16:00:00", "25.45");

	}

	@Test
	void test3() {

		testPrices("2020-06-14T21:00:00", "35.50");

	}

	@Test
	void test4() {

		testPrices("2020-06-15T10:00:00", "30.50");

	}

	@Test
	void test5() {

		testPrices("2020-06-16T21:00:00", "38.95");

	}

	private void testPrices(String date, String price) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		Date dateParam = new Date();
		try {
			dateParam = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PriceRequestDto priceRequestDto = new PriceRequestDto();
		priceRequestDto.setBrandId(Long.valueOf(1));
		priceRequestDto.setProductId(Long.valueOf(35455));
		priceRequestDto.setDate(dateParam);

		PriceResponseDto priceResponseDto = priceController.findPrices(priceRequestDto).getBody();

		assertEquals(price, priceResponseDto.getProductPrice().toString());
	}

}
