package com.hairSalon.userProfileService.hairdresserLocations;

import com.hairSalon.userProfileService.hairdressers.Hairdressers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_hairdresser_locations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HairdresserLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hairdresser_id", nullable = false)
    private Hairdressers hairdresser;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city_id", nullable = false)
    private Long cityId;
}
