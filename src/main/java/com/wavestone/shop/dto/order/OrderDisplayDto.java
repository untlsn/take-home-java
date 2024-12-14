package com.wavestone.shop.dto.order;

import com.wavestone.shop.domain.OrderHeader;
import com.wavestone.shop.domain.OrderHeaderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDisplayDto {
	public Long headerId;
	public LocalDateTime headerOrderDate;
	public OrderHeaderStatus headerStatus;

	public String customerEmail;
	public List<OrderDisplayLineDto> orderLines;


	public OrderDisplayDto(OrderHeader orderHeader) {
		headerId = orderHeader.getId();
		headerOrderDate = orderHeader.getOrderDate();
		headerStatus = orderHeader.getStatus();
		customerEmail = orderHeader.getCustomer().getEmail();



		orderLines = orderHeader.getOrderLines().stream()
			.map(OrderDisplayLineDto::new)
			.collect(Collectors.toList());
	}
}
