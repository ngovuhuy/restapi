package com.example.restapi.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.ProfileDTO;
import com.example.restapi.service.CustomUserDetailsService;
import com.example.restapi.service.ProfileService;
import com.example.restapi.service.TokenBlacklistService;
import com.example.restapi.util.JwtTokenUtil;

import io.AuthRequest;
import io.AuthResponse;
import io.ProfileRequest;
import io.ProfileResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor

public class AuthController {

	
	 private final ModelMapper modelMapper;
	 private final ProfileService profileService;
	 private final AuthenticationManager authenticationManager;
	 private final JwtTokenUtil jwtTokenUtil;
	 private final CustomUserDetailsService userDetailsService;
	 
	 private final TokenBlacklistService tokenBlacklistService;
	 
	 @ResponseStatus(HttpStatus.CREATED)
	 @PostMapping("/register")
	public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
		 ProfileDTO profileDTO =  maptoProfileDTO(profileRequest);
		profileDTO =  profileService.createProfile(profileDTO);
		return mapToProfileResponse(profileDTO);
	}
     
	 @PostMapping("/login")
	 public AuthResponse authenticateProfile(@RequestBody AuthRequest authRequest) throws Exception {
		 log.info("api login", authRequest);
		 authenticate(authRequest);
		final UserDetails userDetails =  userDetailsService.loadUserByUsername(authRequest.getEmail());
		 final String token = jwtTokenUtil.generateToken(userDetails);
		 return new AuthResponse(token, authRequest.getEmail());
	 }
	 
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 @PostMapping("/signout")
	 public void signout(HttpServletRequest request){
		String jwtToken = extractJwtTokenFromRequest(request);
		if(jwtToken != null) {
			tokenBlacklistService.addTokenToBlacklist(jwtToken);
		}
	 }
	 
	 private String extractJwtTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			 return bearerToken.substring(7);
			 
		}
		return null;
	}

	private void authenticate(AuthRequest authRequest) throws Exception {
      try {
 		 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
      }catch(DisabledException ex) {
    	  throw new Exception("Profile disable");
      }catch(BadCredentialsException ex) {
    	  throw new Exception("Bad Bredentials");
      }
	 }
	private ProfileDTO maptoProfileDTO(@Valid ProfileRequest profileRequest) {
		return modelMapper.map(profileRequest, ProfileDTO.class);
	}

	private ProfileResponse mapToProfileResponse(ProfileDTO profileDTO) {
		return modelMapper.map(profileDTO, ProfileResponse.class);
	}
}
