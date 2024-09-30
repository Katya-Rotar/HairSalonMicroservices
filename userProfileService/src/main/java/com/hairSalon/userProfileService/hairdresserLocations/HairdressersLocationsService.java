package com.hairSalon.userProfileService.hairdresserLocations;

import com.hairSalon.userProfileService.hairdressers.Hairdressers;
import com.hairSalon.userProfileService.hairdressers.HairdressersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HairdressersLocationsService {
    private final HairdressersLocationsRepository hairdressersLocationsRepository;
    private final HairdressersRepository hairdressersRepository;

    public List<HairdressersLocationsRequest> findHairdressersLocationsByHairdresserId(Long hairdresserId){
        List<HairdresserLocations> hairdresserLocations = hairdressersLocationsRepository
                .findByHairdresserId(hairdresserId);
        return hairdresserLocations.stream().map(this::mapToHairdressersLocationsRequest).toList();
    }

    public void createHairdresserLocations(HairdressersLocationsRequest request){
        HairdresserLocations hairdresserLocations = mapToHairdresserLocations(request);
        hairdressersLocationsRepository.save(hairdresserLocations);
    }

    public void updateHairdresserLocations(Long id, HairdressersLocationsRequest request){
        HairdresserLocations locations = hairdressersLocationsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hairdresser location not found"));
        Hairdressers hairdresser = hairdressersRepository.findById(request.hairdresserId())
                .orElseThrow(() -> new IllegalArgumentException("Hairdresser not found"));

        locations.setHairdresser(hairdresser);
        locations.setAddress(request.address());
        locations.setCityId(request.cityId());
        hairdressersLocationsRepository.save(locations);
    }

    public void deleteHairdresserLocations(Long hairdresserId){
        List<HairdresserLocations> locations = hairdressersLocationsRepository.findByHairdresserId(hairdresserId);
        hairdressersLocationsRepository.deleteAll(locations);
    }

    private HairdressersLocationsRequest mapToHairdressersLocationsRequest(HairdresserLocations hairdresserLocations){
        return new HairdressersLocationsRequest(
                hairdresserLocations.getHairdresser().getId(),
                hairdresserLocations.getAddress(),
                hairdresserLocations.getCityId()
        );
    }

    private HairdresserLocations mapToHairdresserLocations(HairdressersLocationsRequest request){
        Hairdressers hairdresser = hairdressersRepository.findById(request.hairdresserId())
                .orElseThrow(() -> new IllegalArgumentException("Hairdresser not found"));

        HairdresserLocations hairdresserLocations = new HairdresserLocations();
        hairdresserLocations.setHairdresser(hairdresser);
        hairdresserLocations.setAddress(request.address());
        hairdresserLocations.setCityId(request.cityId());
        return hairdresserLocations;
    }
}
