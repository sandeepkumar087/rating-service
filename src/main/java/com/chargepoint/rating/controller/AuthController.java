package com.chargepoint.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chargepoint.rating.model.LoginRequest;
import com.chargepoint.rating.model.UserRegistration;
import com.chargepoint.rating.service.AuthService;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
    	String response = authService.authenticate(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/registration")
    public ResponseEntity<Object> userRegistration(@RequestBody UserRegistration registration){
    	
    	String response = authService.userRegistration(registration);
    	return new ResponseEntity<>(response, HttpStatus.CREATED);
    	
    }

}

