package com.ss501.myplaypayment.domain.payment.event;

import java.util.Date;

import lombok.Data;

@Data
public class PayApproved extends AbstractEvent {

	private Long id;
	
	private Long reservationId;
	
	private String paymentStatus;
	
	private Integer price;
	
	private Date approvalDate;
}

