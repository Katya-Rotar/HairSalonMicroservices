package com.hairSalon.appointments.hairdresserService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userProfileService", url = "${user-profile-service.url}")
public interface UserProfileFeignClient {
    @GetMapping("/api/hairdressers/{id}")
    boolean getHairdresserById(@PathVariable("id") Long hairdresserId);
}

