package com.zara.price.application.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zara.price.application.request.PriceRequest;
import com.zara.price.application.response.PriceResponse;
import com.zara.price.domain.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan(basePackages = "com.zara.price")
class PriceControllerIntegrationTest {

	@Autowired
	PriceController priceController;

	@Test
	void testGetPriceAt20200614T10() {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setBrandId(Long.valueOf(1));
		priceRequest.setProductId(Long.valueOf(35455));
		priceRequest.setDate(formatStringToDate("2020-06-14T10:00:00"));

		PriceResponse priceResponse = priceController.findPrices(priceRequest).getBody();

		assertEquals("35.50", priceResponse.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponse.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponse.getProductId());
		assertEquals(formatStringToDate("2020-06-14T00:00:00"), priceResponse.getStartDate());
		assertEquals(formatStringToDate("2020-12-31T23:59:59"), priceResponse.getEndDate());

	}

	@Test
	void testGetPriceAt20200614T16() {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setBrandId(Long.valueOf(1));
		priceRequest.setProductId(Long.valueOf(35455));
		priceRequest.setDate(formatStringToDate("2020-06-14T16:00:00"));

		PriceResponse priceResponse = priceController.findPrices(priceRequest).getBody();

		assertEquals("25.45", priceResponse.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponse.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponse.getProductId());
		assertEquals(formatStringToDate("2020-06-14T15:00:00"), priceResponse.getStartDate());
		assertEquals(formatStringToDate("2020-06-14T18:30:00"), priceResponse.getEndDate());

	}

	@Test
	void testGetPriceAt20200614T21() {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setBrandId(Long.valueOf(1));
		priceRequest.setProductId(Long.valueOf(35455));
		priceRequest.setDate(formatStringToDate("2020-06-14T21:00:00"));

		PriceResponse priceResponse = priceController.findPrices(priceRequest).getBody();

		assertEquals("35.50", priceResponse.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponse.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponse.getProductId());
		assertEquals(formatStringToDate("2020-06-14T00:00:00"), priceResponse.getStartDate());
		assertEquals(formatStringToDate("2020-12-31T23:59:59"), priceResponse.getEndDate());

	}

	@Test
	void testGetPriceAt20200615T10() {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setBrandId(Long.valueOf(1));
		priceRequest.setProductId(Long.valueOf(35455));
		priceRequest.setDate(formatStringToDate("2020-06-15T10:00:00"));

		PriceResponse priceResponse = priceController.findPrices(priceRequest).getBody();

		assertEquals("30.50", priceResponse.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponse.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponse.getProductId());
		assertEquals(formatStringToDate("2020-06-15T00:00:00"), priceResponse.getStartDate());
		assertEquals(formatStringToDate("2020-06-15T11:00:00"), priceResponse.getEndDate());

	}

	@Test
	void testGetPriceAt20200616T21() {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setBrandId(Long.valueOf(1));
		priceRequest.setProductId(Long.valueOf(35455));
		priceRequest.setDate(formatStringToDate("2020-06-16T21:00:00"));

		PriceResponse priceResponse = priceController.findPrices(priceRequest).getBody();

		assertEquals("38.95", priceResponse.getProductPrice().toString());
		assertEquals(Long.valueOf(1), priceResponse.getBrandId());
		assertEquals(Long.valueOf(35455), priceResponse.getProductId());
		assertEquals(formatStringToDate("2020-06-15T16:00:00"), priceResponse.getStartDate());
		assertEquals(formatStringToDate("2020-12-31T23:59:59"), priceResponse.getEndDate());

	}

	@Test
	void testGetPriceResourceNotFoundException() {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setBrandId(Long.valueOf(1));
		priceRequest.setProductId(Long.valueOf(35455));
		priceRequest.setDate(formatStringToDate("2022-06-16T21:00:00"));

		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> priceController.findPrices(priceRequest));

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
