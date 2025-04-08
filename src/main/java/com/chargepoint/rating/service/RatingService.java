package com.chargepoint.rating.service;

import java.util.List;
import java.util.Optional;

import com.chargepoint.rating.entity.Rating;

public interface RatingService {

	public Rating createRating(Rating rating);

	public Optional<Rating> getRating(Long id);

	public List<Rating> getRatingsByStation(Long stationId);

	//public List<Rating> getModeratedRatingsByStation(Long stationId, boolean moderated);
	double getAverageRatingForStation(Long stationId);

	public Rating moderateRating(Long id, boolean approved);

	public Rating addReviewPoints(Long id, int points);

	public Rating updateRating(Long id, Rating updatedRating);

	public void deleteRating(Long id);

	List<Rating> getAllRatings();

	List<Rating> getRatingByNumberOfStars(int starRating);

}
