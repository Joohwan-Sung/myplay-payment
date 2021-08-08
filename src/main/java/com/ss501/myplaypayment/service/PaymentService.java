package com.ss501.myplaypayment.service;

import java.util.List;

import com.ss501.myplaypayment.domain.payment.entity.Payment;

public interface PaymentService {
	
	List<Payment> findAllPayment();

	Payment save(Payment payment);

}
