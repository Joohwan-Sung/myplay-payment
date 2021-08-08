package com.ss501.myplaypayment.domain.payment.vo;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CreditCard {

	private String creditCardOwner;
	private String creditCardNo;
}
