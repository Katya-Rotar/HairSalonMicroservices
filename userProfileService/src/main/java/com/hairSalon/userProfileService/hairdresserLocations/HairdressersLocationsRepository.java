package com.hairSalon.userProfileService.hairdresserLocations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HairdressersLocationsRepository extends JpaRepository<HairdresserLocations, Long> {
    List<HairdresserLocations> findByHairdresserId(Long hairdresserId);
}
