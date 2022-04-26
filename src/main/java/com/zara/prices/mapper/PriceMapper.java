package com.zara.prices.mapper;

import org.mapstruct.Mapper;

import com.zara.prices.dto.PriceDto;
import com.zara.prices.dto.PriceResponseDto;
import com.zara.prices.model.Price;

@Mapper(componentModel = "spring")
public interface PriceMapper {

	PriceDto priceToPriceDto(Price prices);

	Price priceDtoToPrice(PriceDto pricesDto);

	PriceResponseDto priceToResponsePriceDto(Price price);

}
