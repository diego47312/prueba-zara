package com.zara.prices.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequestDto {

	@NotNull(message = "La marca es obligatoria")
	private Long brandId;

	@NotNull(message = "El producto es obligatorio")
	private Long productId;

	@NotNull(message = "La fecha es obligatoria")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
	private Date date;

}
