package com.app.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeliveryResponse {
	private Long deliveryId;
	private Long orderId;
	private LocalDate deliveryDate;
	private String status;
}
