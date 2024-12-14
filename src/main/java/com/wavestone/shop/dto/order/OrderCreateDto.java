package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.Customer;
import com.wavestone.shop.domain.OrderHeader;
import com.wavestone.shop.domain.OrderHeaderStatus;
import com.wavestone.shop.domain.OrderLine;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Record with minimal data to create OrderHeader and connected OrderLines
 */
public record OrderCreateDto(
	String description, List<OrderLineCreateDto> orderLine, Long customer
) {

	/**
	 * Return OrderHeader with connected OrderLines based on OrderCreateDto so you can save it to database
	 */
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
