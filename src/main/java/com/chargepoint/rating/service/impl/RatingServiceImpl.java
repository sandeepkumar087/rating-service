package com.chargepoint.rating.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chargepoint.rating.entity.Rating;
import com.chargepoint.rating.exception.RatingNotFoundException;
import com.chargepoint.rating.repository.RatingRepository;
import com.chargepoint.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
    private RatingRepository ratingRepository;

	@Override
    public Rating createRating(Rating rating) {
        if (rating.getStarRating() < 1 || rating.getStarRating() > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5");
        }
       // String user = "Admin";
       // rating.setCreatedBy(user);
//        rating.setCreatedDate(LocalDateTime.now());
//        rating.setUpdatedBy(user);
//        rating.setUpdatedDate(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

	@Override
    public Optional<Rating> getRating(Long id) {
        return ratingRepository.findById(id);
    }


	@Override
    public List<Rating> getRatingsByStation(Long stationId) {
        List<Rating> ratings = ratingRepository.findByStationId(stationId);
        if (ratings.isEmpty()) {
            throw new RatingNotFoundException("No ratings found for station: " + stationId);
        }
        return ratings;
    }


//	@Override
//	 public List<Rating> getModeratedRatingsByStation(Long stationId, boolean moderated) {
//        return ratingRepository.findByStationIdAndModerated(stationId, moderated);
//    }
	
	@Override
    public Rating moderateRating(Long id, boolean approved) {
        Optional<Rating> ratingOpt = ratingRepository.findById(id);
        if (ratingOpt.isPresent()) {
            Rating rating = ratingOpt.get();
            rating.setModerated(approved);
            rating.setUpdatedBy("Admin");
            //rating.setUpdatedDate(LocalDateTime.now());
            return ratingRepository.save(rating);
        }
        throw new RatingNotFoundException("Rating not found with id: " + id);
    }

	@Override
    public Rating addReviewPoints(Long id, int points) {
        Optional<Rating> ratingOpt = ratingRepository.findById(id);
        if (ratingOpt.isPresent()) {
            Rating rating = ratingOpt.get();
            if (points < 0) {
                throw new IllegalArgumentException("Points cannot be negative");
            }
            rating.setReviewPoints(rating.getReviewPoints() + points);
            rating.setUpdatedBy("Admin");
            //rating.setUpdatedDate(LocalDateTime.now());
            return ratingRepository.save(rating);
        }
        throw new RatingNotFoundException("Rating not found with id: " + id);
    }

	@Override
    public Rating updateRating(Long id, Rating updatedRating) {
        Optional<Rating> existingRating = ratingRepository.findById(id);
        if (existingRating.isPresent()) {
            Rating rating = existingRating.get();
            rating.setStarRating(updatedRating.getStarRating());
            rating.setTextReview(updatedRating.getTextReview());
            rating.setUpdatedBy(updatedRating.getUserId());
            //rating.setUpdatedDate(LocalDateTime.now());
            return ratingRepository.save(rating);
        }
        throw new RatingNotFoundException("Rating not found with id: " + id);
    }

	@Override
    public void deleteRating(Long id) {
        if (!ratingRepository.existsById(id)) {
            throw new RatingNotFoundException("Rating not found with id: " + id);
        }
        ratingRepository.deleteById(id);
    }

	@Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

	/**
	 * 
	 */
    @Override
    public List<Rating> getRatingByNumberOfStars(int starRating) {
        return ratingRepository.findByStarRating(starRating);
    }

    /**
     * 
     */
	@Override
	public double getAverageRatingForStation(Long stationId) {
		List<Rating> ratings = ratingRepository.findByStationId(stationId);
	    if (ratings.isEmpty()) {
	        return 0.0;
	    }
	    double total = ratings.stream().mapToInt(Rating::getStarRating).sum();
	    return total / ratings.size();
	}


}
