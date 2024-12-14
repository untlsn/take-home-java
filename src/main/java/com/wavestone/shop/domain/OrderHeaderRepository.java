package com.wavestone.shop.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {

	Page<OrderHeader> findById(Long orderId, Pageable pageable);
	Page<OrderHeader> findByStatus(OrderHeaderStatus status, Pageable pageable);
	Page<OrderHeader> findByCustomerEmail(String customerEmail, Pageable pageable);
	@Query(
		"select o from OrderHeader o left join o.orderLines l where l.product.name = :productName"
	)
	Page<OrderHeader> findByProductName(String productName, Pageable pageable);
}
