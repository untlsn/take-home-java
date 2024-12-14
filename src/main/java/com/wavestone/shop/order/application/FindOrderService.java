package com.wavestone.shop.order.application;

import com.wavestone.shop.domain.OrderHeaderRepository;
import com.wavestone.shop.dto.order.OrderDisplayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindOrderService {

	private final OrderHeaderRepository orderHeaderRepository;

	@Transactional(readOnly = true)
	public List<OrderDisplayDto> findAllOrders() {
		return orderHeaderRepository.findAll().stream()
			.map(OrderDisplayDto::new)
			.collect(Collectors.toList());
	}
}
