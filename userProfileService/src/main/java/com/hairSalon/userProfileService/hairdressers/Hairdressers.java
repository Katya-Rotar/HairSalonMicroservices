package com.hairSalon.userProfileService.hairdressers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_hairdressers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hairdressers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "portfolio")
    private String portfolio;

    @Column(name = "certificates")
    private String certificates;

    @Column(name = "rating", nullable = false, columnDefinition = "DECIMAL(2,1) DEFAULT 0.0")
    private BigDecimal rating;

    @Column(name = "is_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isVerified;
}
