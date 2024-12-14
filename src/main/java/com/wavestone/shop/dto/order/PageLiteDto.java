package com.wavestone.shop.dto.order;

import java.util.List;

/**
 * Simplest Page wrapper that only contain totalPages and data. Feel free to use it as a base for other PageDtos
 */
public record PageLiteDto<TData>(
	Integer totalPages,
	List<TData> data
) {}
