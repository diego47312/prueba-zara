package com.zara.prices.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.prices.dto.PriceRequestDto;
import com.zara.prices.dto.PriceResponseDto;
import com.zara.prices.exception.ResourceNotFoundException;
import com.zara.prices.service.PricesService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PricesController.class)
class PricesControllerTest {

	@MockBean
	PricesService priceService;

	@Autowired
	MockMvc mockMvc;

	@Test
	void testFindPircesOk() throws Exception {
		PriceResponseDto priceResponseDto = new PriceResponseDto(
				Long.valueOf(1L), 
				Long.valueOf(1L), 
				formatStringToDate("2020-06-15T16:00:00"), 
				formatStringToDate("2020-12-31T23:59:59"), 
				Long.valueOf(35455), 
				BigDecimal.valueOf(38.95));
		
		PriceRequestDto priceRequestDto = new PriceRequestDto(
				Long.valueOf(1),
				Long.valueOf(35455),
				formatStringToDate("2020-06-16T21:00:00"));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(priceRequestDto);
		
		Mockito.when(priceService.findPriceByDateAndProductAndBrand(priceRequestDto)).thenReturn(priceResponseDto);
	 
		mockMvc.perform(
			post("/api/prices/findPrices")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status().isFound())
			.andExpect(jsonPath("id", Matchers.is(1)))
			.andExpect(jsonPath("brandId", Matchers.is(1)))
			.andExpect(jsonPath("startDate", Matchers.is("2020-06-15T16:00:00")))
			.andExpect(jsonPath("endDate", Matchers.is("2020-12-31T23:59:59")))
			.andExpect(jsonPath("productId", Matchers.is(35455)))
			.andExpect(jsonPath("productPrice", Matchers.is(38.95)));

	}
	
	@Test
	void testFindPircesResourceNotFound() throws Exception {
		
		PriceRequestDto priceRequestDto = new PriceRequestDto(
				Long.valueOf(1),
				Long.valueOf(35455),
				formatStringToDate("2020-06-16T21:00:00"));
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(priceRequestDto);
		
		Mockito.when(priceService.findPriceByDateAndProductAndBrand(priceRequestDto))
	      .thenThrow(new ResourceNotFoundException("No hay precio disponible"));
	 
		mockMvc.perform(
			post("/api/prices/findPrices")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status().isNotFound())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
			.andExpect(result -> assertEquals("No hay precio disponible", result.getResolvedException().getMessage()));

	}
	
	@Test
	void testFindPircesMethodArgumentNotValidException() throws Exception {
		
		PriceResponseDto priceResponseDto = new PriceResponseDto(
				Long.valueOf(1L), 
				Long.valueOf(1L), 
				formatStringToDate("2020-06-15T16:00:00"), 
				formatStringToDate("2020-12-31T23:59:59"), 
				Long.valueOf(35455), 
				BigDecimal.valueOf(38.95));
		
		PriceRequestDto priceRequestDto = new PriceRequestDto(
				null,
				null,
				null);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(priceRequestDto);
		
		Mockito.when(priceService.findPriceByDateAndProductAndBrand(priceRequestDto)).thenReturn(priceResponseDto);
	 
		mockMvc.perform(
			post("/api/prices/findPrices")
			.contentType(MediaType.APPLICATION_JSON)
			.content(json))
			.andExpect(status().isBadRequest())
			.andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

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
