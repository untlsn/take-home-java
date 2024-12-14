package com.wavestone.shop.order.application;

import com.wavestone.shop.domain.*;
import com.wavestone.shop.dto.order.OrderCreateDto;
import com.wavestone.shop.dto.order.OrderLineCreateDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManageOrderService {
	private final OrderHeaderRepository orderHeaderRepository;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;

	public Long createOrder(OrderCreateDto order) throws EntityNotFoundException {
		var customer = customerRepository.findById(order.customer())
			.orElseThrow(() -> new EntityNotFoundException("Customer not found"));
		var orderLines = convertToOrderLines(order.orderLine());

		var newOrder = orderHeaderRepository.save(
			order.toOrderHeader(customer, orderLines)
		);

		var id = newOrder.getId();
		log.info("Saved order {}", id);
		return id;
	}

	/**
	 * Convert list of OrderLineDto to list of OrderLine models
	 */
	List<OrderLine> convertToOrderLines(List<OrderLineCreateDto> orderLinesDto) {
		if (orderLinesDto == null || orderLinesDto.isEmpty())
			throw new IllegalArgumentException("OrderLines cannot be empty");

		return orderLinesDto.stream().map(line -> {
			var product = productRepository.findById(line.product())
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
			return line.toOrderLine(product);
		}).collect(Collectors.toList());
	}
}
