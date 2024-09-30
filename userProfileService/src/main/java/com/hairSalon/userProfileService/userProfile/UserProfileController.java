package com.hairSalon.userProfileService.userProfile;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userProfile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    // Отримання профілю за userId через параметр запиту
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserProfileRequest> getUserProfileByUserId(@RequestParam Long userId){
        return userProfileService.getUserProfileByUserId(userId);
    }

    // Створення нового профілю
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUserProfile(@RequestBody UserProfileRequest userProfileRequest){
        userProfileService.createUserProfile(userProfileRequest);
        return "User Profile Added Successfully";
    }

    // Оновлення профілю
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateUserProfile(@PathVariable Long id, @RequestBody UserProfileRequest request) {
        userProfileService.updateUserProfile(id, request);
        return "User Profile Updated Successfully";
    }

    // Видалення профілю
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserProfile(@PathVariable Long id){
        userProfileService.deleteUserProfile(id);
    }
}
