package com.hairSalon.appointments.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServicesService {
    private final ServicesRepository servicesRepository;
    public void placeService(ServicesRequest servicesRequest){
        var service = mapToServices(servicesRequest);
        servicesRepository.save(service);
    }
    public List<ServicesRequest> getAllServices(){
        List<Services> services = servicesRepository.findAll();
        return services.stream().map(this::mapToServicesRequest).toList();
    }

    private ServicesRequest mapToServicesRequest(Services services){
        return new ServicesRequest(services.getId(), services.getServiceName());
    }
    private static Services mapToServices(ServicesRequest servicesRequest){
        Services services = new Services();
        services.setServiceName(servicesRequest.service_name());
        return services;
    }
}
