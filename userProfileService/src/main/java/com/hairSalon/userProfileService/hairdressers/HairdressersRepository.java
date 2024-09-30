package com.hairSalon.userProfileService.hairdressers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HairdressersRepository extends JpaRepository<Hairdressers, Long> {
    List<Hairdressers> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
