package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.Customer;
import com.wavestone.shop.domain.OrderHeader;
import com.wavestone.shop.domain.OrderHeaderStatus;
import com.wavestone.shop.domain.OrderLine;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
	String description, List<OrderLineDto> orderLine, Long customer
) {
	public OrderHeader toOrderHeader(Customer customer, List<OrderLine> lines) {
		var orderHeader = new OrderHeader();

		orderHeader.setDescription(description);
		orderHeader.setCustomer(customer);
		orderHeader.setOrderDate(LocalDateTime.now());
		orderHeader.setStatus(OrderHeaderStatus.CREATED);

		for (var orderLine: lines) {
			orderHeader.addLine(orderLine);
		}

		return orderHeader;
	}
}
