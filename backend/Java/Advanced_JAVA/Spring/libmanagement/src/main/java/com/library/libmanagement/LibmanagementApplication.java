package com.library.libmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibmanagementApplication.class, args);

//		PasswordEncoder enc = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		System.out.println(enc.encode("1234"));
	}
}