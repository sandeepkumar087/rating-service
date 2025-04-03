package com.chargepoint.rating.model;



import com.chargepoint.rating.config.UserRole;

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
	private UserRole role;
	
//	public UserRegistration() {
//		
//	}
//	public UserRegistration(String userName, String fullName, String password, String mobileNo, String email,
//			UserRole role) {
//		super();
//		this.userName = userName;
//		this.fullName = fullName;
//		this.password = password;
//		this.mobileNo = mobileNo;
//		this.email = email;
//		this.role = role;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getFullName() {
//		return fullName;
//	}
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getMobileNo() {
//		return mobileNo;
//	}
//	public void setMobileNo(String mobileNo) {
//		this.mobileNo = mobileNo;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public UserRole getRole() {
//		return role;
//	}
//	public void setRole(UserRole role) {
//		this.role = role;
//	}
	

}
