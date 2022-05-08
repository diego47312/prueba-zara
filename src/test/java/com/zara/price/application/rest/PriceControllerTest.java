package com.zara.price.application.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.zara.price.domain.Price;
import com.zara.price.domain.PriceMapper;
import com.zara.price.domain.ResourceNotFoundException;
import com.zara.price.domain.service.PriceService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PriceController.class)
@ComponentScan(basePackageClasses = { PriceMapper.class })
class PriceControllerTest {

	@MockBean
	PriceService priceService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testFindPircesOk() throws Exception {
		Price price = new Price(
			Long.valueOf(1L), 
			Long.valueOf(1L), 
			formatStringToDate("2020-06-15T16:00:00"), 
			formatStringToDate("2020-12-31T23:59:59"), 
			Long.valueOf(35455), 
			Long.valueOf(1L), 
			BigDecimal.valueOf(38.95),
			"EUR");
		
		Mockito.when(priceService.findPriceByDateAndProductAndBrand(Mockito.any())).thenReturn(price);
	 
		mockMvc.perform(
			get("/api/prices")
			.contentType(MediaType.APPLICATION_JSON)
			.param("brandId", "1")
			.param("productId", "35455")
			.param("date", "2023-06-16T21:00:00"))
			.andExpect(status().isFound())
			.andExpect(jsonPath("$.id", Matchers.is(1)))
			.andExpect(jsonPath("$.brandId", Matchers.is(1)))
			.andExpect(jsonPath("$.startDate", Matchers.is("2020-06-15T16:00:00")))
			.andExpect(jsonPath("$.endDate", Matchers.is("2020-12-31T23:59:59")))
			.andExpect(jsonPath("$.productId", Matchers.is(35455)))
			.andExpect(jsonPath("$.productPrice", Matchers.is(38.95)));

	}

	@Test
	void testFindPircesResourceNotFound() throws Exception {
		
		

		Mockito.when(priceService.findPriceByDateAndProductAndBrand(Mockito.any())).thenThrow(new ResourceNotFoundException("No hay precio disponible"));

		mockMvc.perform(get("/api/prices")
				.contentType(MediaType.APPLICATION_JSON)
				.param("brandId", "1")
				.param("productId", "35455")
				.param("date", "2023-06-16T21:00:00"))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andExpect(result -> assertEquals("No hay precio disponible", result.getResolvedException().getMessage()));

	}

	@Test
	void testFindPircesThrowableException() throws Exception {

		Mockito.when(priceService.findPriceByDateAndProductAndBrand(Mockito.any())).thenThrow(new NullPointerException());

		mockMvc.perform(
				get("/api/prices")
				.param("brandId", "1")
				.param("productId", "1")
				.param("date", "2023-06-16T21:00:00")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());

	}

	@Test
	void testFindPircesMethodArgumentNotValidException() throws Exception {
	 
		mockMvc.perform(
			get("/api/prices")
			.contentType(MediaType.APPLICATION_JSON)
			.param("brandId", "1")
			.param("productId", "1")
			.param("date", ""))
			.andDo(print())
			.andExpect(status().isBadRequest());

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
