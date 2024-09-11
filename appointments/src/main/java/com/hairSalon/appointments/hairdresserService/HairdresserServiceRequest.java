package com.hairSalon.appointments.hairdresserService;

public record HairdresserServiceRequest(
        Long hairdresserId,
        Long serviceId,
        double servicePrice,
        int serviceDuration
) { }
