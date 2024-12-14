package com.wavestone.shop.adapters.rest.order;

import com.wavestone.shop.order.application.FindOrderFilterEnum;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
public class OrderGetterDto {

	Integer page = 0;
	Sort.Direction sortDirection = Sort.Direction.ASC;
	String sortBy = "id";
	FindOrderFilterEnum filterBy;
	String filter = "";

	public Pageable pageable() {
		return PageRequest.of(page == null ? 0 : page, 10, sort());
	}

	private Sort sort() {
		return Sort.by(sortDirection, sortBy);
	}

	public Boolean haveValidFilter() {
		return (filter == null && filterBy == null) || (filter != null && filterBy != null);
	}
}
