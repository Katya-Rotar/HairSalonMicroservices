package com.hairSalon.appointments;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppointmentsApplicationTests {
	// Створюємо контейнер для MySQL
	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

	// Встановлюємо порт, на якому працює локальний сервер
	@LocalServerPort
	private Integer port;

	// Налаштування базового URI для тестів
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	// Запуск контейнера з MySQL до початку тестів
	static {
		mySQLContainer.start();
	}

	// Тест на додавання послуги для перукаря
	@Test
	void shouldAddHairdresserService() {
		String addHairdresserServiceJson = """
        {
            "hairdresserId": 1,
            "serviceId": 2,
            "servicePrice": 50.00,
            "serviceDuration": 60
        }
        """;

		// Відправляємо POST-запит і перевіряємо, що статус відповіді - 201 (створено)
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(addHairdresserServiceJson)
				.when()
				.post("/api/hairdresserServices")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		// Перевіряємо, що у відповіді отримали правильне повідомлення
		assertThat(responseBodyString, Matchers.is("Hairdresser Service Placed Successfully"));
	}

	// Тест на отримання всіх послуг для перукаря
	@Test
	void shouldGetAllHairdresserServices() {
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/hairdresserServices")
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.body().asString();

		// Перевіряємо, що відповідь містить певні дані (можна уточнити залежно від тестових даних)
		assertThat(responseBodyString, Matchers.containsString("serviceId"));
	}
}
