package com.example.restapi.dto;



import java.sql.Timestamp;

import io.ProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
	private String profileId;
	private String email;
	private String name;
	private String password;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
