package com.example.restapi.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

	private Set<String> blacklist = new HashSet<String>();
	
	public void addTokenToBlacklist(String token) {
		blacklist.add(token);
	}
	public boolean isTokenBlackListed(String token) {
		return blacklist.contains(token);
	}
}
