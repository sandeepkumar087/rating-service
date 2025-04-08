package com.chargepoint.rating.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chargepoint.rating.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stations", schema = "public")
public class Station extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String stationName;
	private String areaName;
	private String pincode;

	@OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("station-ratings")
	private List<Rating> ratings = new ArrayList<>();

	@PrePersist
	protected void prePersist() {
		this.setCreatedDate(new Date());
		this.setUpdatedDate(new Date());
	}
}