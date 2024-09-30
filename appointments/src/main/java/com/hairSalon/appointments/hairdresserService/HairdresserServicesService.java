package com.hairSalon.appointments.hairdresserService;

import com.hairSalon.appointments.hairdresserService.feign.UserProfileFeignClient;
import com.hairSalon.appointments.services.Services;
import com.hairSalon.appointments.services.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HairdresserServicesService {
    private final HairdresserServiceRepository hairdresserServiceRepository;
    private final ServicesRepository servicesRepository;
    private final UserProfileFeignClient userProfileFeignClient;
    public void addHairdresserService(HairdresserServiceRequest request) {
        // Перевірка, чи існує перукар
        boolean result = userProfileFeignClient.getHairdresserById(request.hairdresserId());
        if (!result) {
            throw new RuntimeException("Hairdresser not found");
        }

        // Додавання послуги для існуючого перукаря
        HairdresserService service = mapToHairdresserService(request);
        hairdresserServiceRepository.save(service);
    }
    public void updateHairdresserService(Long id, HairdresserServiceRequest request) {
        HairdresserService service = hairdresserServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        service.setHairdresser(request.hairdresserId());
        service.setServicePrice(request.servicePrice());
        service.setServiceDuration(request.serviceDuration());
        hairdresserServiceRepository.save(service);
    }

    public void deleteHairdresserService(Long id) {
        hairdresserServiceRepository.deleteById(id);
    }
    public List<HairdresserServiceRequest> getAllHairdresserServices() {
        List<HairdresserService> services = hairdresserServiceRepository.findAll();
        return services.stream().map(this::mapToHairdresserServiceRequest).toList();
    }
    public List<HairdresserServiceRequest> getServicesByHairdresser(Long hairdresserId) {
        List<HairdresserService> services = hairdresserServiceRepository.findByHairdresser(hairdresserId);
        return services.stream().map(this::mapToHairdresserServiceRequest).toList();
    }
    public List<HairdresserServiceRequest> getServicesByService(Long serviceId){
        List<HairdresserService> services = hairdresserServiceRepository.findByServiceId(serviceId);
        return services.stream().map(this::mapToHairdresserServiceRequest).toList();
    }
    private HairdresserServiceRequest mapToHairdresserServiceRequest(HairdresserService service) {
        return new HairdresserServiceRequest( service.getHairdresser(), service.getService().getId(),
                service.getServicePrice(), service.getServiceDuration()
        );
    }
    private HairdresserService mapToHairdresserService(HairdresserServiceRequest request) {
        Services service = servicesRepository.findById(request.serviceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        HairdresserService hairdresserService = new HairdresserService();
        hairdresserService.setHairdresser(request.hairdresserId());
        hairdresserService.setService(service);
        hairdresserService.setServicePrice(request.servicePrice());
        hairdresserService.setServiceDuration(request.serviceDuration());
        return hairdresserService;
    }
}
