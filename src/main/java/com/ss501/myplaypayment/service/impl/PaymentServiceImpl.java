package com.ss501.myplaypayment.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ss501.myplaypayment.domain.payment.entity.Payment;
import com.ss501.myplaypayment.domain.payment.repository.PaymentRepository;
import com.ss501.myplaypayment.service.PaymentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> findAllPayment() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment save(Payment payment) {
		paymentRepository.save(payment);
		return null;
	}

}
