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
public class UserRegistrationResponse {
	//private String username;
	private boolean success;
    private String message;
    //private T data;
    
	

}
