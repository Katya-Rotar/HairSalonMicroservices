package com.hairSalon.userProfileService.hairdresserLocations;

public record HairdressersLocationsRequest(
        Long hairdresserId,
        String address,
        Long cityId
) {
}
