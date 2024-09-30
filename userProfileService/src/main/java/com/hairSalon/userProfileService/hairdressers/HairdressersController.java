package com.hairSalon.userProfileService.hairdressers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hairdressers")
@RequiredArgsConstructor
public class HairdressersController {
    private final HairdressersService hairdressersService;
    // Отримання профілю за userId через параметр запиту
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HairdressersRequest> findHairdressersByUserId(@RequestParam Long userId){
        return hairdressersService.findHairdressersByUserId(userId);
    }

    @GetMapping("/{id}")
    public boolean getHairdresserById(@PathVariable("id") Long hairdresserId) {
        return hairdressersService.findHairdresserById(hairdresserId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createHairdresserProfile(@RequestBody HairdressersRequest hairdressersRequest){
        hairdressersService.createHairdresserProfile(hairdressersRequest);
        return "Hairdresser Added Successfully";
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateHairdresserProfile(@PathVariable Long id, @RequestBody HairdressersRequest request) {
        hairdressersService.updateHairdresserProfile(id, request);
        return "Hairdresser Updated Successfully";
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHairdresserProfile(@PathVariable Long id){
        hairdressersService.deleteHairdresserProfile(id);
    }
}
