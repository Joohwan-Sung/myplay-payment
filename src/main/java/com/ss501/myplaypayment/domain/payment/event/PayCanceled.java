package com.ss501.myplaypayment.domain.payment.event;

import java.util.Date;

import lombok.Data;

@Data
public class PayCanceled extends AbstractEvent {
//Payment Service - PayCanceled
	
	private Long id;
	
	private Long reservationId;
	
	private String paymentStatus;
	
	private Integer price;
	
	private Date approvalDate;
	
}