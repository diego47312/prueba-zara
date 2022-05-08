package com.zara.price.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Price {

//	PRICE_LIST: Identificador de la tarifa de precios aplicable.
	private Long id;

//	BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
	private Long brandId;

//	START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
	private Date startDate;

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
