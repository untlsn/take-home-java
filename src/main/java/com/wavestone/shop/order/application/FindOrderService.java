package com.wavestone.shop.order.application;

import com.wavestone.shop.adapters.rest.order.OrderGetterDto;
import com.wavestone.shop.domain.OrderHeaderRepository;
import com.wavestone.shop.domain.OrderHeaderStatus;
import com.wavestone.shop.dto.order.OrderDisplayDto;
import com.wavestone.shop.dto.order.PageLiteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindOrderService {

	private final OrderHeaderRepository orderHeaderRepository;

	@Transactional(readOnly = true)
	public PageLiteDto<OrderDisplayDto> findAllOrders(OrderGetterDto orderGetterDto) {
		if (!orderGetterDto.haveValidFilter()) {
			throw new IllegalStateException("filter and filterBy must be used at the same time");
		}

		var entries = switch (orderGetterDto.getFilterBy()) {
			case orderId -> orderHeaderRepository.findById(
			Long.parseLong(orderGetterDto.getFilter()),
			orderGetterDto.pageable()
		);
			case status -> orderHeaderRepository.findByStatus(
			OrderHeaderStatus.fromString(orderGetterDto.getFilter()),
			orderGetterDto.pageable()
		);
			case customerEmail -> orderHeaderRepository.findByCustomerEmail(
			orderGetterDto.getFilter(),
			orderGetterDto.pageable()
		);
			case productName -> orderHeaderRepository.findByProductName(
			orderGetterDto.getFilter(),
			orderGetterDto.pageable()
		);
			default -> orderHeaderRepository.findAll(orderGetterDto.pageable());
		};

		return new PageLiteDto<>(
			entries.getTotalPages(),
			entries.stream()
				.map(OrderDisplayDto::new)
				.collect(Collectors.toList())
		);
	}
}
