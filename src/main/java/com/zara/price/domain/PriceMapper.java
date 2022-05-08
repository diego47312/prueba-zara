package com.zara.price.domain;

import org.mapstruct.Mapper;

import com.zara.price.application.response.PriceResponse;

import lombok.Generated;

@Mapper(componentModel = "spring")
@Generated
public interface PriceMapper {

	PriceResponse priceToResponsePrice(Price price);

}
