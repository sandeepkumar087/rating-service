package com.chargepoint.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chargepoint.rating.model.LoginRequest;
import com.chargepoint.rating.model.LoginResponse;
import com.chargepoint.rating.model.UserRegistration;
import com.chargepoint.rating.model.UserRegistrationResponse;
import com.chargepoint.rating.service.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
    private AuthService authService;

	/***
	 * 
	 * @param request
	 * @return response
	 */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
    	LoginResponse response = authService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /***
     * 
     * @param registration
     * @return registrationResponse
     */
    @PostMapping("/registration")
    public ResponseEntity<Object> userRegistration(@RequestBody UserRegistration registration){
    	
    	String response = authService.userRegistration(registration);
    	
    	UserRegistrationResponse registrationResponse = new UserRegistrationResponse(true, response);
    	
    	return new ResponseEntity<>(registrationResponse, HttpStatus.CREATED);
    	
    }

}

