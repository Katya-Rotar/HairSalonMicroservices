package com.hairSalon.appointments.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeService(@RequestBody ServicesRequest servicesRequest) {
        servicesService.placeService(servicesRequest);
        return "Service Placed Successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServicesRequest> getAllServices() {
        return servicesService.getAllServices();
    }
}

