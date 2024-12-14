package com.wavestone.shop.adapters.rest.order;

import com.wavestone.shop.order.application.FindOrderFilterEnum;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Dto record collecting RequestParam of order getter.
 * Just push all request params to constructor
 */
public record OrderGetterDto(
	Integer page,
	Sort.Direction sortDirection,
	String sortBy,
	Optional<FindOrderFilterEnum> filterBy,
	String filter
) {

	/**
	 * Its give empty Optional when filterBy is empty of filter is null, so you can be sure that filtering is necessary
	 */
	public Optional<FindOrderFilterEnum> getFilterBy() {
		return filter == null ? Optional.empty() : filterBy;
	}

	/**
	 * Generate from page, sortDirection and sortBy Pageable object used in JpaRepositories
	 */
	public Pageable pageable() {
		return PageRequest.of(page, 10, sort());
	}

	private Sort sort() {
		return Sort.by(sortDirection, sortBy);
	}

	/**
	 * Check if filter and filterBy both exist or not so you can throw exception if it returns false
	 */
	public Boolean haveValidFilter() {
		return (filter == null && filterBy == null) || (filter != null && filterBy != null);
	}
}
