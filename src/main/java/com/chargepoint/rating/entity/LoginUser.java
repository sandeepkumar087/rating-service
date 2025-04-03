package com.chargepoint.rating.entity;

import java.util.Date;
import java.util.List;

import com.chargepoint.rating.config.UserRole;
import com.chargepoint.rating.model.BaseModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LOGIN_USER", schema = "public")
public class LoginUser extends BaseModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "MOBILE_NO")
	private String mobileNo;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private UserRole role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "loginUserId")
    private List<UserPasswordHistory> passwordHistory;
	
	@PrePersist
	protected void prePersist() {
		this.setCreatedDate(new Date());
		this.setUpdatedDate(new Date());
	}
    
}
