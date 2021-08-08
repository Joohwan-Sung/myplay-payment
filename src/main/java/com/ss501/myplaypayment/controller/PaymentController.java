package com.ss501.myplaypayment.controller;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss501.myplaypayment.domain.payment.entity.Payment;
import com.ss501.myplaypayment.domain.payment.enums.PaymentStatus;
import com.ss501.myplaypayment.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class PaymentController {
	
	private PaymentService paymentService;
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
	
	//전체 Payments 조회
	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> findAllPayment() {
		// VIEW 는 PayApproved 를 가져와야되나 ?? mybnb 참조하기.
		return ResponseEntity.ok().body(paymentService.findAllPayment());
	}
	
	//payment 등록
	@PostMapping("/payments")
	public ResponseEntity<Payment> saveReservation(@RequestBody Payment payment) {
		log.info("Json : " + payment.toString());
		Payment pm = paymentService.save(payment);
		//TODO
		//kafka 돌리고 bff, payment run 해서 확인
		return ResponseEntity.ok().body(pm);
	}

}
