package com.hairSalon.userProfileService.userProfile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    List<UserProfile> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
