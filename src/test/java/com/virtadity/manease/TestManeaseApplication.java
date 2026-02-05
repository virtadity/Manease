package com.virtadity.manease;

import org.springframework.boot.SpringApplication;

public class TestManeaseApplication {

	public static void main(String[] args) {
		SpringApplication.from(ManeaseApplication::main).with(AppTestContainersConfig.class).run(args);
	}

}
