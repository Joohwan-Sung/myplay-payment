package com.ss501.myplaypayment.domain.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatus {
	REQUESTED,
	APPROVED,
	CANCELED
}
