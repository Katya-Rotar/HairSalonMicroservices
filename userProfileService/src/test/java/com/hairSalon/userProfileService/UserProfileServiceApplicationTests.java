package com.hairSalon.userProfileService;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.hamcrest.Matchers;
import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.given;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserProfileServiceApplicationTests {

	// Створюємо контейнер для PostgreSQL
	static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:15.3")
			.withDatabaseName("user_profile_service")
			.withUsername("postgres")
			.withPassword("postgres");

	@LocalServerPort
	private Integer port;

	// Налаштування базового URI для тестів
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	// Запуск контейнера з PostgreSQL до початку тестів
	static {
		postgresContainer.start();
	}

	// Тест на створення профілю користувача
	@Test
	void shouldCreateUserProfile() {
		String createUserProfileJson = """
        {
            "userId": 1,
            "firstName": "John",
            "lastName": "Doe",
            "profilePicture": "profilePic.jpg"
        }
        """;

		var responseBodyString = given()
				.contentType("application/json")
				.body(createUserProfileJson)
				.when()
				.post("/api/userProfile")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		assertThat(responseBodyString, Matchers.is("User Profile Added Successfully"));
	}
}
