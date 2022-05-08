package com.zara.price.infraestructure.repository;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "PRICES")
@Data
@RequiredArgsConstructor
public class PriceEntity {

//	PRICE_LIST: Identificador de la tarifa de precios aplicable.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

//	BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
	@Column(name = "BRAND_ID", nullable = false)
	private Long brandId;

//	START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
	@Column(name = "START_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(name = "END_DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

//	PRODUCT_ID: Identificador código de producto.
	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

//	PRIORITY: Desambiguado de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
	@Column(name = "PRIORITY", nullable = false)
	private Long priority;

//	PRICE: precio final de venta.
	@Column(name = "PRICE", nullable = false)
	private BigDecimal productPrice;

//	CURR: iso de la moneda.
	@Column(name = "CURR", length = 3, nullable = false)
	private String curr;

}
