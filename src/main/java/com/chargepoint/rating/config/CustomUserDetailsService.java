package com.chargepoint.rating.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chargepoint.rating.entity.LoginUser;
import com.chargepoint.rating.repository.LoginUserRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private LoginUserRepository loginUserRepository;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LoginUser user = loginUserRepository.findByUserName(userName);
		if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + userName);
        }
		return new org.springframework.security.core.userdetails.User(userName, user.getPassword(), new ArrayList<>());
	}

}
