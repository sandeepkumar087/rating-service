package com.chargepoint.rating.service;

import com.chargepoint.rating.model.LoginRequest;
import com.chargepoint.rating.model.UserRegistration;

public interface AuthService {

	String authenticate(LoginRequest request);

	String userRegistration(UserRegistration registration);

}