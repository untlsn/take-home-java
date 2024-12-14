package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.OrderLine;

public class OrderDisplayLineDto {
	public Long id;
	public Integer quantity;

	public OrderDisplayLineDto(OrderLine orderLine) {
		id = orderLine.getId();
		quantity = orderLine.getQuantity();
	}
}
