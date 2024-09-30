package com.hairSalon.userProfileService.userProfile;

public record UserProfileRequest(
        Long userId,
        String firstName,
        String lastName,
        String profilePicture
) {
}
