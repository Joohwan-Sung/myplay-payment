package com.ss501.myplaypayment.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.ss501.myplaypayment.app.config.kafka.KafkaProcessor;
import com.ss501.myplaypayment.domain.payment.entity.Payment;
import com.ss501.myplaypayment.domain.payment.enums.PaymentStatus;
import com.ss501.myplaypayment.domain.payment.event.ReservationCanceled;
import com.ss501.myplaypayment.domain.payment.repository.PaymentRepository;

@Service
public class PolicyHandler{
	
	@Autowired
    PaymentRepository paymentRepository;
	PaymentStatus paymentStatus;
	
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){
    	
    }

//    예약 취소 메시지 수신 시 payment 상태 변경
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCanceled_Cancel(@Payload ReservationCanceled reservationCanceled){
        if(reservationCanceled.isMe()){
            Payment payment = new Payment();
            payment.setReservationId(reservationCanceled.getId());
            
            payment.setPaymentStatus(paymentStatus.CANCELED);
            paymentRepository.save(payment);
        }
    }

}

