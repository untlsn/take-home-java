package com.wavestone.shop.adapters.rest.order;

import com.wavestone.shop.order.application.FindOrderService;
import com.wavestone.shop.order.application.ManageOrderService;
import com.wavestone.shop.dto.order.OrderDisplayDto;
import com.wavestone.shop.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
	private final ManageOrderService manageOrderService;
	private final FindOrderService findOrderService;

	@GetMapping
	public List<OrderDisplayDto> getAllOrders() {
		return findOrderService.findAllOrders();
	}

	@PostMapping
	public ResponseEntity<Long> createOrder(
		@RequestBody OrderDto order
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