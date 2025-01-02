package com.example.restapi.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.restapi.entity.ProfileEntity;
import com.example.restapi.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;

    public ProfileEntity getLoggedInProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("No authentication found");
        }

        // Lấy email từ authentication, giống như trong loadUserByUsername
        final String email = authentication.getName();
        log.info("Inside getLoggedInProfile()::: Email: {}", email);

        // Truy vấn ProfileEntity từ repository
        ProfileEntity profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Profile not found for the email " + email));

        log.info("Profile found: {}", profile);
        return profile;
    }
}
