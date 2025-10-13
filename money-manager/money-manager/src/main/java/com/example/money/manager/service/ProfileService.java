package com.example.money.manager.service;

import com.example.money.manager.dto.ProfileDto;
import com.example.money.manager.entity.ProfileEntity;
import com.example.money.manager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDto registerProfile (ProfileDto profileDto){
        ProfileEntity newProfile = toEntity(profileDto);
        newProfile.setActiveToken(UUID.randomUUID().toString());
        newProfile = profileRepository.save(newProfile);
        return toDto(newProfile);
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

    public ProfileEntity toEntity (ProfileDto profileDto){
        return ProfileEntity.builder()
                .id(profileDto.getId())
                .fullName(profileDto.getFullName())
                .email(profileDto.getEmail())
                .password(profileDto.getPassword())
                .profileImageUrl(profileDto.getProfileImageUrl())
                .createdAt(profileDto.getCreatedAt())
                .updatedAt(profileDto.getUpdatedAt())
                .build();
    }
}
