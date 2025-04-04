package com.chargepoint.rating.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chargepoint.rating.entity.LoginUser;
import com.chargepoint.rating.entity.UserPasswordHistory;
import com.chargepoint.rating.model.LoginRequest;
import com.chargepoint.rating.model.UserRegistration;
import com.chargepoint.rating.repository.LoginUserRepository;
import com.chargepoint.rating.service.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginUserServiceImpl implements AuthService{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
    private LoginUserRepository loginUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret-key}")
    private String secretKey;


	@Override
	public String authenticate(LoginRequest request) {
		LoginUser user = loginUserRepository.findByUserName(request.getUserName());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return generateToken(user);
	}
	
    

    @SuppressWarnings("deprecation")
	private String generateToken(LoginUser user) {
//        return Jwts.builder()
//                .setSubject(user.getUserName())
//                .claim("name", user.getFullName())
//                .claim("type", user.getRole())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
    	return Jwts.builder()
                .setSubject(user.getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1-hour expiry
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

	@Override
	public String userRegistration(UserRegistration registration) {
		
		LoginUser user = modelMapper.map(registration, LoginUser.class);
		UserPasswordHistory hist = new UserPasswordHistory();
		String pass = passwordEncoder.encode(user.getPassword());
		hist.setLoginUserId(user);
		hist.setPassword(pass);
		hist.setCreatedBy(user.getUserName());
		hist.setUpdatedBy(user.getUserName());
		List<UserPasswordHistory> list = Arrays.asList(hist);
		user.setPasswordHistory(list);
		user.setPassword(pass);
		user.setCreatedBy(registration.getUserName());
		user.setUpdatedBy(registration.getUserName());
		loginUserRepository.save(user);
		return "Register is success";
	}

}
