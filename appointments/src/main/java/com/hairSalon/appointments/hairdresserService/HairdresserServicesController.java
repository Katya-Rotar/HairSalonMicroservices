package com.hairSalon.appointments.hairdresserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hairdresserServices")
@RequiredArgsConstructor
public class HairdresserServicesController {
    private final HairdresserServicesService hairdresserServicesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addHairdresserService(@RequestBody HairdresserServiceRequest hairdresserServiceRequest){
        hairdresserServicesService.addHairdresserService(hairdresserServiceRequest);
        return "Hairdresser Service Added Successfully";
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateHairdresserService(@PathVariable Long id, @RequestBody HairdresserServiceRequest request) {
        hairdresserServicesService.updateHairdresserService(id, request);
        return "Hairdresser Service Updated Successfully";
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHairdresserService(@PathVariable Long id){
        hairdresserServicesService.deleteHairdresserService(id);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HairdresserServiceRequest> getAllHairdresserServices() {
        return hairdresserServicesService.getAllHairdresserServices();
    }
    @GetMapping("/hairdresser/{hairdresserId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HairdresserServiceRequest> getServicesByHairdresser(@PathVariable Long hairdresserId) {
        return hairdresserServicesService.getServicesByHairdresser(hairdresserId);
    }
    @GetMapping("/service/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public List<HairdresserServiceRequest> getServicesByService(@PathVariable Long serviceId) {
        return hairdresserServicesService.getServicesByService(serviceId);
    }
}
