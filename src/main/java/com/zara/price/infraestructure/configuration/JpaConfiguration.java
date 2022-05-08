package com.zara.price.infraestructure.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zara.price.infraestructure.repository.SpringDataJpaDbPriceRepository;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaDbPriceRepository.class)
public class JpaConfiguration {

}
