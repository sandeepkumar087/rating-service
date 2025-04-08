package com.chargepoint.rating.repository;

import com.chargepoint.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByStationId(Long stationId);

	// Correct method using "id" (as JPA recognizes it from @Id in Station)
	//List<Rating> findByStationIdAndModerated(Long id, boolean moderated);

	//@Query(nativeQuery = true, name = "select * from ratings r where r.star_rating =#starRating")
	List<Rating> findByStarRating(int starRating);
	
}