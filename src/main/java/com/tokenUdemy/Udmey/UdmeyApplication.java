package com.tokenUdemy.Udmey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
//@ComponentScan("com.tokenUdemy.Udmey.controller") //optional
public class UdmeyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdmeyApplication.class, args);
	}

}
