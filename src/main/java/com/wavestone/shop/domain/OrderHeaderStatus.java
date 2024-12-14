package com.wavestone.shop.domain;

public enum OrderHeaderStatus {
	CREATED, IN_DELIVERY, COMPLETED;


	public static OrderHeaderStatus fromString(String status) {
		return switch (status.toUpperCase()) {
			case "CREATED" -> OrderHeaderStatus.CREATED;
			case "IN_DELIVERY" -> OrderHeaderStatus.IN_DELIVERY;
			case "COMPLETED" -> OrderHeaderStatus.COMPLETED;
			default -> throw new IllegalStateException("Unexpected value: " + status);
		};
	}
}
