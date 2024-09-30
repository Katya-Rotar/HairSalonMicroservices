package com.hairSalon.userProfileService.hairdressers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HairdressersService {
    private final HairdressersRepository hairdressersRepository;
    // Отримання інформації про перукаря за ID
    public List<HairdressersRequest> findHairdressersByUserId(Long userId){
        List<Hairdressers> hairdressers = hairdressersRepository.findByUserId(userId);
        return hairdressers.stream().map(this::mapToHairdressersRequest).toList();
    }
    // Створення нового профілю перукаря
    public void createHairdresserProfile(HairdressersRequest request) {
        Hairdressers hairdressers = mapToHairdressers(request);
        hairdressersRepository.save(hairdressers);
    }
    // Оновлення інформації про профіль перукаря
    public void updateHairdresserProfile(Long id, HairdressersRequest request) {
        Hairdressers hairdressers = hairdressersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hairdressers not found"));
        hairdressers.setPortfolio(request.portfolio());
        hairdressers.setCertificates(request.certificates());
        hairdressers.setRating(request.rating());
        hairdressers.setVerified(request.isVerified());
        hairdressersRepository.save(hairdressers);
    }
    // Видалення профілю перукаря
    public void deleteHairdresserProfile(Long userId) {
        hairdressersRepository.deleteByUserId(userId);
    }
    private HairdressersRequest mapToHairdressersRequest(Hairdressers hairdressers){
        return  new HairdressersRequest(hairdressers.getUserId(), hairdressers.getPortfolio(),
                hairdressers.getCertificates(), hairdressers.getRating(), hairdressers.isVerified());
    }
    private Hairdressers mapToHairdressers (HairdressersRequest hairdressersRequest){
        Hairdressers hairdressers = new Hairdressers();
        hairdressers.setUserId(hairdressersRequest.userId());
        hairdressers.setPortfolio(hairdressersRequest.portfolio());
        hairdressers.setCertificates(hairdressersRequest.certificates());
        hairdressers.setRating(hairdressersRequest.rating());
        hairdressers.setVerified(hairdressersRequest.isVerified());
        return hairdressers;
    }
}
