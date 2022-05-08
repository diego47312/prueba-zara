package com.zara.price.infraestructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zara.price.domain.repository.PriceRepository;
import com.zara.price.domain.service.DomainPriceService;
import com.zara.price.domain.service.PriceService;

@Configuration
@ComponentScan(basePackages = "com.zara.price")
public class BeanConfiguration {

	@Bean
	PriceService priceService(final PriceRepository priceRepository) {
		return new DomainPriceService(priceRepository);
	}
}
