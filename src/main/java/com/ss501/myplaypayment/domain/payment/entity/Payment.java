package com.ss501.myplaypayment.domain.payment.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.ss501.myplaypayment.domain.payment.enums.PaymentStatus;
import com.ss501.myplaypayment.domain.payment.event.PayApproved;
import com.ss501.myplaypayment.domain.payment.event.PayCanceled;
import com.ss501.myplaypayment.domain.payment.vo.CreditCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "credit_card")
	@Embedded
	private CreditCard creditCard;

	@Column(name = "reservation_id")
	private Long reservationId;

	@Column(name = "payment_status")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	@Column(name = "price")
	private Integer price;

	@Column(name = "approval_date")
	private Date approvalDate;

	@PostPersist
	public void onPostPersist() {

		if (paymentStatus.APPROVED.equals(getPaymentStatus())) {
			
			// REQUESTED 상태의 payment 가 만들어지면 PayApproved 이벤트 발행
			
			PayApproved payApproved = new PayApproved();
			payApproved.setPaymentStatus(getPaymentStatus().toString());
			BeanUtils.copyProperties(this, payApproved);
			payApproved.publishAfterCommit();
			
			// 결제이력을 저장한 후 적당한 시간 끌기
            try {
                Thread.sleep((long) (400 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			
			System.out.println("--- PayApproved event publishAfterCommit");
            
			
		} else if (paymentStatus.CANCELED.equals(getPaymentStatus())) {
			// PayCanceled 이벤트 발행
			PayCanceled payCanceled = new PayCanceled();
			payCanceled.setPaymentStatus(getPaymentStatus().toString());
			payCanceled.setApprovalDate(getApprovalDate());
			payCanceled.setPrice(getPrice());
			BeanUtils.copyProperties(this, payCanceled);
			payCanceled.publishAfterCommit();
			// 결제이력을 저장한 후 적당한 시간 끌기
            try {
                Thread.sleep((long) (400 + Math.random() * 220));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         
			System.out.println("--- PayCanceled event publishAfterCommit");
		}



	}

}
