package com.wavestone.shop.order.application;

import com.wavestone.shop.adapters.rest.order.OrderGetterDto;
import com.wavestone.shop.domain.OrderHeader;
import com.wavestone.shop.domain.OrderHeaderRepository;
import com.wavestone.shop.domain.OrderHeaderStatus;
import com.wavestone.shop.dto.order.OrderDisplayDto;
import com.wavestone.shop.dto.order.PageLiteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindOrderService {

	private final OrderHeaderRepository orderHeaderRepository;

	/**
	 * Return PageLiteDto (List of data and pagination metadata) based on orderGetterDto page, filter and sort data
	 */
	@Transactional(readOnly = true)
	public PageLiteDto<OrderDisplayDto> findAllOrders(OrderGetterDto orderGetterDto) {
		var entries = getOrderHeadersByFilter(orderGetterDto);

		return new PageLiteDto<>(
			entries.getTotalPages(),
			entries.stream()
				.map(OrderDisplayDto::new)
				.collect(Collectors.toList())
		);
	}

	Page<OrderHeader> getOrderHeadersByFilter(OrderGetterDto orderGetterDto) {
		return orderGetterDto.getFilterBy()
			.map(filterBy -> switch (filterBy) {
				case orderId -> orderHeaderRepository.findById(
					Long.parseLong(orderGetterDto.filter()),
					orderGetterDto.pageable()
				);
				case status -> orderHeaderRepository.findByStatus(
					OrderHeaderStatus.fromString(orderGetterDto.filter()),
					orderGetterDto.pageable()
				);
				case customerEmail -> orderHeaderRepository.findByCustomerEmail(
					orderGetterDto.filter(),
					orderGetterDto.pageable()
				);
				case productName -> orderHeaderRepository.findByProductName(
					orderGetterDto.filter(),
					orderGetterDto.pageable()
				);
			})
			.orElseGet(() -> orderHeaderRepository.findAll(orderGetterDto.pageable()));
	}
}
