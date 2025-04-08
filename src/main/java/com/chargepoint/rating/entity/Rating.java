package com.chargepoint.rating.entity;

import com.chargepoint.rating.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ratings", schema = "public")
public class Rating extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Custom ID like "SR-XXXX"
	private String userId;
	private int starRating;
	private String textReview;
	private boolean moderated;
	private int reviewPoints;
	
	@ManyToOne
    @JoinColumn(name = "station_id", nullable = false) // Foreign key reference to Station
	@JsonBackReference("station-ratings") // Use the same reference name as in `@JsonManagedReference`
	@JsonIgnore
    private Station station;

}