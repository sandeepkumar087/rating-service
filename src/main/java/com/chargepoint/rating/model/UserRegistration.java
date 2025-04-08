package com.chargepoint.rating.model;



import com.chargepoint.rating.config.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {
	
	private String userName;
	private String fullName;
	private String password;
	private String mobileNo;
	private String email;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
}
