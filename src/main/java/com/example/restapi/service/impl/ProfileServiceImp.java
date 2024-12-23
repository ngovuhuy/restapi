package com.example.restapi.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restapi.dto.ProfileDTO;
import com.example.restapi.entity.ProfileEntity;
import com.example.restapi.exceptions.ItemExistsException;
import com.example.restapi.repository.ProfileRepository;
import com.example.restapi.service.ProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService {
	private final ProfileRepository profileRepository;
	private final ModelMapper modelMapper;

	private final PasswordEncoder encoder;
	@Override
	public ProfileDTO createProfile(ProfileDTO profileDTO) {
		  if(profileRepository.existsByEmail(profileDTO.getEmail())) {
			  throw new ItemExistsException("Profile already exists " + profileDTO.getEmail());
		  }
	      profileDTO.setPassword(encoder.encode(profileDTO.getPassword()));
	       ProfileEntity profileEntity =  mapToProfileEntity(profileDTO);
	       profileEntity.setProfileId(UUID.randomUUID().toString());
	       // call the repository method
	      
	     profileEntity =  profileRepository.save(profileEntity);
	     return mapToProfileDTO(profileEntity);
	}

	private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
		return modelMapper.map(profileEntity, ProfileDTO.class);
	}

	private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
		return modelMapper.map(profileDTO, ProfileEntity.class);
	}

}
