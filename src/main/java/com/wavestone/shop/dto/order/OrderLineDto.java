package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.OrderLine;
import com.wavestone.shop.domain.Product;

public record OrderLineDto(
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
