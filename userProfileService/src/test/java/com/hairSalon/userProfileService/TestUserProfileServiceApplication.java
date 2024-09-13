package com.hairSalon.userProfileService;

import org.springframework.boot.SpringApplication;

public class TestUserProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(UserProfileServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
