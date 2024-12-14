package com.wavestone.shop.adapters.rest.order;

import com.wavestone.shop.dto.order.OrderDisplayDto;
import com.wavestone.shop.dto.order.PageLiteDto;
import com.wavestone.shop.order.application.FindOrderFilterEnum;
import com.wavestone.shop.order.application.FindOrderService;
import com.wavestone.shop.order.application.ManageOrderService;
import com.wavestone.shop.dto.order.OrderCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
	private final ManageOrderService manageOrderService;
	private final FindOrderService findOrderService;

	@GetMapping
	public PageLiteDto<OrderDisplayDto> getAllOrders(
		@RequestParam(name = "page", defaultValue = "0") Integer page,
		@RequestParam(name = "sortDirection", defaultValue = "ASC") Sort.Direction sortDirection,
		@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
		@RequestParam(name = "filterBy", required = false) Optional<FindOrderFilterEnum> filterBy,
		@RequestParam(name = "filter", required = false) String filter
	) {
		return findOrderService.findAllOrders(new OrderGetterDto(
			page,
			sortDirection,
			sortBy,
			filterBy,
			filter
		));
	}

	@PostMapping
	public ResponseEntity<Long> createOrder(
		@RequestBody OrderCreateDto order
	) {
		var id = manageOrderService.createOrder(order);

		return ResponseEntity.created(
			ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(id)
				.toUri()
		).body(id);
	}
}
