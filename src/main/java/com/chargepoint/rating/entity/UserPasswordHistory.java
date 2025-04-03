package com.chargepoint.rating.entity;


import java.util.Date;

import com.chargepoint.rating.model.BaseModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "USER_PASSWORD_HISTORY", schema = "public")
public class UserPasswordHistory extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LOGIN_USER_ID")
	private LoginUser loginUserId;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@PrePersist
	protected void prePersist() {
		this.setCreatedDate(new Date());
		this.setUpdatedDate(new Date());
	}

}
