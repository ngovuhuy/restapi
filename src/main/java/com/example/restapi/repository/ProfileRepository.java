package com.example.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapi.entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>{

	
}
