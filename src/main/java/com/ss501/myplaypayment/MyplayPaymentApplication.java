package com.ss501.myplaypayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;

import com.ss501.myplaypayment.app.config.kafka.KafkaProcessor;

@SpringBootApplication
@EnableBinding(KafkaProcessor.class)
//@EnableFeignClients 설정파일 추가필요
public class MyplayPaymentApplication {
	
	public static ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MyplayPaymentApplication.class, args);
//		SpringApplication.run(MyplayPaymentApplication.class, args);
	}

}