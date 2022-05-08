
package com.zara.price.infraestructure.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaDbPriceRepository extends JpaRepository<PriceEntity, Long> {

	@Query("select p from PriceEntity p " 
			+ "where p.brandId=:brandId " 
			+ "and p.productId=:productId " 
			+ "and :date BETWEEN p.startDate and p.endDate ")
	List<PriceEntity> findByBrandIdAndProductIdAndDate(@Param("brandId") Long brandId, @Param("productId") Long productId, @Param("date") Date date);

}
