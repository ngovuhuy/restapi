package com.example.restapi.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.restapi.entity.ProfileEntity;
import com.example.restapi.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final ProfileRepository profileRepository;
	
	public ProfileEntity getLoggedInProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final String email = authentication.getName();
		System.out.println("Authentication: " + authentication);
		System.out.println("Authentication Name: " + authentication.getName());
		return profileRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Profile not found for the email1 "+email));
	}
}
