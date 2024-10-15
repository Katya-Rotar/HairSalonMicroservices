package com.hairSalon.appointments.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${server.port}")
    int port;
    @Bean
    public OpenAPI hairdresserServiceAPI() {
        var server = new Server().url("http://localhost:" + port);
        return new OpenAPI()
                .servers(List.of(server))
                .info(new Info().title("Hairdresser Service API")
                        .description("This is the REST API for Hairdresser Service")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")));
    }
}

