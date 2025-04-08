package com.chargepoint.rating.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	private String username;
	private String session;
	private String token;
	private String message;

}
