package com.wavestone.shop.dto.order;

import java.util.List;

public record PageLiteDto<TData>(
	Integer totalPages,
	List<TData> data
) {}
