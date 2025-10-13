package com.example.money.manager.controller;

import com.example.money.manager.dto.ProfileDto;
import com.example.money.manager.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<ProfileDto> registerProfile(@RequestBody ProfileDto profileDto){
        ProfileDto registeredProfile = profileService.registerProfile(profileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
    }

    @GetMapping("/active")
    public ResponseEntity<String> activeProfile(@RequestParam String token){
        boolean isActive = profileService.activeProfile(token);
        if(isActive){
            return ResponseEntity.ok("Profile is active");
        }else{
            return ResponseEntity.badRequest().body("Profile is not active");
        }
    }
}
