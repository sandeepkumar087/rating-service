package com.chargepoint.controller;

import com.chargepoint.rating.controller.AuthController;
import com.chargepoint.rating.entity.LoginUser;
import com.chargepoint.rating.model.LoginRequest;
import com.chargepoint.rating.model.LoginResponse;
import com.chargepoint.rating.model.UserRegistration;
import com.chargepoint.rating.model.UserRegistrationResponse;
import com.chargepoint.rating.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserRegistration() {
        // Arrange
        UserRegistration request = new UserRegistration();
        LoginUser user = new LoginUser();
        request.setUserName("testuser");
        request.setPassword("password");
        request.setEmail("test@example.com");
        request.setFullName("Test User");
        request.setMobileNo("1234567890");
        request.setRole(user.getRole());

        when(authService.userRegistration(any(UserRegistration.class)))
                .thenReturn("Register is success");
        // Act
        ResponseEntity<Object> response = authController.userRegistration(request);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserRegistrationResponse);
        UserRegistrationResponse registrationResponse = (UserRegistrationResponse) response.getBody();
        assertTrue(registrationResponse.isSuccess());
        assertEquals("Register is success", registrationResponse.getMessage());
    }
    
    @Test
    void testLoginUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("testuser");
        loginRequest.setPassword("password");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("mock-jwt-token");
        loginResponse.setMessage("Login successful");

        when(authService.authenticate(any(LoginRequest.class)))
                .thenReturn(loginResponse);

        // Act
        ResponseEntity<Object> response = authController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof LoginResponse);
        LoginResponse result = (LoginResponse) response.getBody();
        assertEquals("mock-jwt-token", result.getToken());
        assertEquals("Login successful", result.getMessage());
    }
}
