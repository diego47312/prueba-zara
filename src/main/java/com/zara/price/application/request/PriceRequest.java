package com.zara.price.application.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {

	@NotNull(message = "La marca es obligatoria")
	private Long brandId;

	@NotNull(message = "El producto es obligatorio")
	private Long productId;

	@NotNull(message = "La fecha es obligatoria")
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date date;

}
