package com.hairSalon.appointments;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
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

	@Test
	void addHairdresserService_HairdresserExists() {
		// Stub for checking if the hairdresser exists
		stubFor(get(urlEqualTo("/api/hairdressers/1"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "application/json")
						.withBody("true")));

		// Create a service first
		String serviceJson = """
    {
        "service_name": "Haircut"
    }
    """;

		var createServiceResponse = RestAssured.given()
				.contentType("application/json")
				.body(serviceJson)
				.when()
				.post("/api/services") // Adjust this endpoint to match your service creation endpoint
				.then()
				.log().all() // Log the response
				.statusCode(201) // Assume the service is created successfully
				.extract()
				.response();

		// Now add the hairdresser service
		String addServiceJson = """
    {
        "hairdresserId": 1,
        "serviceId": 1,
        "servicePrice": 50.0,
        "serviceDuration": 30
    }
    """;

		var response = RestAssured.given()
				.contentType("application/json")
				.body(addServiceJson)
				.when()
				.post("/api/hairdresserServices")
				.then()
				.log().all() // Log the response
				.statusCode(201)
				.extract()
				.response();

		// Ensure the response message is correct
		assertThat(response.getStatusCode(), Matchers.is(201));
		assertThat(response.getBody().asString(), Matchers.is("Hairdresser Service Added Successfully"));

		// Optionally check that the service was created
		var servicesResponse = RestAssured.given()
				.when()
				.get("/api/services")
				.then()
				.statusCode(200)
				.extract()
				.response();

		assertThat(servicesResponse.getBody().asString(), Matchers.containsString("Haircut"));
	}
}
