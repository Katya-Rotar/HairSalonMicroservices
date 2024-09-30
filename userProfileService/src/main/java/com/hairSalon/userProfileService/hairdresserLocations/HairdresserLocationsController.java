package com.hairSalon.userProfileService.hairdresserLocations;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hairdresserLocations")
@RequiredArgsConstructor
public class HairdresserLocationsController {
    private final HairdressersLocationsService hairdressersLocationsService;

    @GetMapping("/{hairdresserId}")
    public List<HairdressersLocationsRequest> getLocationsByHairdresser(@PathVariable Long hairdresserId) {
        return hairdressersLocationsService.findHairdressersLocationsByHairdresserId(hairdresserId);
    }

    @PostMapping
    public void createLocation(@RequestBody HairdressersLocationsRequest request) {
        hairdressersLocationsService.createHairdresserLocations(request);
    }

    @PutMapping("/{id}")
    public void updateLocation(@PathVariable Long id, @RequestBody HairdressersLocationsRequest request) {
        hairdressersLocationsService.updateHairdresserLocations(id, request);
    }

    @DeleteMapping("/{hairdresserId}")
    public void deleteLocations(@PathVariable Long hairdresserId) {
        hairdressersLocationsService.deleteHairdresserLocations(hairdresserId);
    }
}
