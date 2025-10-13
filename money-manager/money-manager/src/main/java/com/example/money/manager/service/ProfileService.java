package com.example.money.manager.service;

import com.example.money.manager.dto.ProfileDto;
import com.example.money.manager.entity.ProfileEntity;
import com.example.money.manager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ProfileDto registerProfile(ProfileDto profileDto) {
        ProfileEntity newProfile = toEntity(profileDto);
        newProfile.setActiveToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);

        String activationLink = "http://localhost:8080/api/v1.0/profile/active?token="+newProfile.getActiveToken();
        String htmlBody = EmailService.buildActivationEmailBody(newProfile.getFullName(), activationLink);

        emailService.sendEmail(newProfile.getEmail(), htmlBody);

        return toDto(newProfile);
    }

    public boolean activeProfile(String activeToken) {
        return profileRepository.findByActiveToken(activeToken)
                .map(profileEntity -> {
                    profileEntity.setIsActive(true);
                    profileRepository.save(profileEntity);
                    return true;
                })
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }

    private ProfileDto toDto(ProfileEntity newProfile) {
        return ProfileDto.builder()
                .id(newProfile.getId())
                .fullName(newProfile.getFullName())
                .email(newProfile.getEmail())
                .profileImageUrl(newProfile.getProfileImageUrl())
                .createdAt(newProfile.getCreatedAt())
                .updatedAt(newProfile.getUpdatedAt())
                .build();
    }

    public ProfileEntity toEntity(ProfileDto profileDto) {
        return ProfileEntity.builder()
                .id(profileDto.getId())
                .fullName(profileDto.getFullName())
                .email(profileDto.getEmail())
                .password(passwordEncoder.encode(profileDto.getPassword()))
                .profileImageUrl(profileDto.getProfileImageUrl())
                .createdAt(profileDto.getCreatedAt())
                .updatedAt(profileDto.getUpdatedAt())
                .build();
    }
}
