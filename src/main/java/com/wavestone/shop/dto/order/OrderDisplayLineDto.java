package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.OrderLine;

/**
 * Part of OrderDisplayDto responsible for OrderLines of OrderHeader
 */
public class OrderDisplayLineDto {
	public Long id;
	public Integer quantity;
	public String productName;

	public OrderDisplayLineDto(OrderLine orderLine) {
		id = orderLine.getId();
		quantity = orderLine.getQuantity();
		productName = orderLine.getProduct().getName();
	}
}
