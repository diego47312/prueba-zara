package com.zara.price.infraestructure.repository;

import java.util.List;

import org.mapstruct.Mapper;

import com.zara.price.domain.Price;

import lombok.Generated;

@Mapper(componentModel = "spring")
@Generated
public interface PriceEntityMapper {

	List<Price> pricesEntityToPrices(List<PriceEntity> prices);

	Price priceEntityToPrice(PriceEntity priceEntity);

}
