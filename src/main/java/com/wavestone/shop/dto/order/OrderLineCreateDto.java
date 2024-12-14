package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.OrderLine;
import com.wavestone.shop.domain.Product;

/**
 * Record with minimal data to create OrderLine and connected OrderLines
 */
public record OrderLineCreateDto(
	String description, Integer quantity, Long product
) {
	public OrderLine toOrderLine(Product product) {
		var orderLine = new OrderLine();

		orderLine.setDescription(description);
		orderLine.setQuantity(quantity);
		orderLine.setProduct(product);

		return orderLine;
	}
}
