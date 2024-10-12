package com.hairSalon.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration(proxyBeanMethods = false)
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> hairdresserServiceRoute() {
        return route("hairdresser_service")
                .route(RequestPredicates.path("/api/hairdresserServices")
                        .or(RequestPredicates.path("/api/hairdresserServices/service/{serviceId}"))
                        .or(RequestPredicates.path("/api/hairdresserServices/hairdresser/{hairdresserId}")), http(
                        System.getenv("HAIRDRESSER_SERVICE_URL")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> serviceRoute() {
        return route("service")
                .route(RequestPredicates.path("/api/services"), http(
                        System.getenv("HAIRDRESSER_SERVICE_URL")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> hairdresserLocationRoute() {
        return route("hairdresser_location")
                .route(RequestPredicates.path("/api/hairdresserLocations")
                        .or(RequestPredicates.path("/api/hairdresserLocations/{id}")), http(
                        System.getenv("USER_PROFILE_SERVICE_URL")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> hairdresserRoute() {
        return route("hairdresser")
                .route(RequestPredicates.path("/api/hairdressers")
                        .or(RequestPredicates.path("/api/hairdressers/{id}")), http(
                        System.getenv("USER_PROFILE_SERVICE_URL")))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> userProfileRoute() {
        return route("user_profile")
                .route(RequestPredicates.path("/api/userProfile"), http(
                        System.getenv("USER_PROFILE_SERVICE_URL")))
                .build();
    }
}
