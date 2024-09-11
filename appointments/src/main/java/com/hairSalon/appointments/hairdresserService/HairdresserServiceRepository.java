package com.hairSalon.appointments.hairdresserService;

import com.hairSalon.appointments.services.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HairdresserServiceRepository extends JpaRepository<HairdresserService, Long> {

    // Метод для пошуку послуг по ідентифікатору перукаря
    List<HairdresserService> findByHairdresser(Long hairdresserId);

    // Метод для пошуку послуг по ідентифікатору послуги
    List<HairdresserService> findByServiceId(Long serviceId);
}
