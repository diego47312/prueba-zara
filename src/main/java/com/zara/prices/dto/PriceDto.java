package com.zara.prices.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDto {

//	PRICE_LIST: Identificador de la tarifa de precios aplicable.
	private Long id;

//	BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
	private Long brandId;

//	START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

//	PRODUCT_ID: Identificador código de producto.
	private Long productId;

//	PRIORITY: Desambiguado de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
	private Long priority;

//	PRICE: precio final de venta.
	private BigDecimal productPrice;

//	CURR: iso de la moneda.
	private String curr;

}
