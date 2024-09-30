package com.hairSalon.userProfileService.userProfile;

import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    // Отримання інформації про профіль користувача за ID
    public List<UserProfileRequest> getUserProfileByUserId(Long userId){
        List<UserProfile> userProfileList = userProfileRepository.findByUserId(userId);
        return userProfileList.stream().map(this::mapToUserProfileRequest).toList();
    }
    // Створення нового профілю
    public void createUserProfile(UserProfileRequest request) {
        UserProfile profile = mapToUserProfile(request);
        userProfileRepository.save(profile);
    }
    // Оновлення інформації про профіль користувача
    public void updateUserProfile(Long id, UserProfileRequest request) {
        // Спочатку знаходимо існуючий профіль за id
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));
        userProfile.setFirstName(request.firstName());
        userProfile.setLastName(request.lastName());
        userProfile.setProfilePicture(request.profilePicture());
        userProfileRepository.save(userProfile);
    }
    // Видалення профілю користувача
    public void deleteUserProfile(Long userId) {
        userProfileRepository.deleteByUserId(userId);
    }
    private UserProfileRequest mapToUserProfileRequest(UserProfile userProfile){
        return new UserProfileRequest(userProfile.getUserId(), userProfile.getFirstName(), userProfile.getLastName(),
                userProfile.getProfilePicture());
    }
    private UserProfile mapToUserProfile(UserProfileRequest request) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(request.userId());
        userProfile.setFirstName(request.firstName());
        userProfile.setLastName(request.lastName());
        userProfile.setProfilePicture(request.profilePicture());
        return userProfile;
    }
}
