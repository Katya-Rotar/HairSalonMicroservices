package com.hairSalon.appointments.hairdresserService;

import com.hairSalon.appointments.services.Services;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_hairdresser_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HairdresserService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "hairdresser_id", nullable = false)
    private Long hairdresser;
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Services service;
    @Column(name = "service_price", nullable = false)
    private double servicePrice;
    @Column(name = "service_duration", nullable = false)
    private int serviceDuration;
}
