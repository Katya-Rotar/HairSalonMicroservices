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

	// Тест на додавання послуги
	@Test
	void addService() {
		String addServiceJson = """
		{
		   "id": 1,
		   "service_name": "Haircut"
		}
		""";

		// Відправляємо POST-запит і перевіряємо, що статус відповіді - 201 (створено)
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.body(addServiceJson)
				.when()
				.post("/api/services")
				.then()
				.log().all()
				.statusCode(201)
				.extract()
				.body().asString();

		// Перевіряємо, що у відповіді отримали правильне повідомлення
		assertThat(responseBodyString, Matchers.is("Service Placed Successfully"));
	}

	// Тест на отримання всіх послуг
	@Test
	void shouldGetAllServices() {
		var responseBodyString = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/services")
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.body().asString();

		// Перевіряємо, що відповідь містить "id" замість "serviceId"
		assertThat(responseBodyString, Matchers.containsString("id"));
	}
}
