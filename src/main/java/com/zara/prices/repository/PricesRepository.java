package com.zara.prices.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zara.prices.model.Price;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {

	@Query("select p from Price p "
			+ "where p.brandId=:brandId "
			+ "and p.productId=:productId "
			+ "and :date BETWEEN p.startDate and p.endDate ")
	List<Price> findByBrandIdAndProductIdAndDate(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("date") Date date);

}
