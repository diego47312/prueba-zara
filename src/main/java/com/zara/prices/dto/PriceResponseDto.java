package com.zara.prices.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PriceResponseDto {

	private Long id;

	private Long brandId;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private Long productId;

	private BigDecimal productPrice;

}
