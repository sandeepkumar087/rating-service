package com.chargepoint.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chargepoint.rating.entity.Rating;
import com.chargepoint.rating.service.RatingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ratings")
@Tag(name = "Rating API", description = "API for managing EV station ratings")

public class RatingController {
	@Autowired
	private RatingService ratingService;

	@PostMapping
	// @PreAuthorize("hasRole('USER')")
	@Operation(summary = "Create a new rating")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Rating created"),
			@ApiResponse(responseCode = "400", description = "Invalid star rating"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		Rating savedRating = ratingService.createRating(rating);
		return ResponseEntity.ok(savedRating);
	}

	@GetMapping("/{id}")
	// @PreAuthorize("hasRole('USER')")
	@Operation(summary = "Get a rating by ID")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Rating found"),
			@ApiResponse(responseCode = "404", description = "Rating not found"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<Rating> getRating(@PathVariable Long id) {
		return ratingService.getRating(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/allRtings")
	public List<Rating> getAllRatings() {
		return ratingService.getAllRatings();
	}

	@GetMapping("/stars")
	public List<Rating> getRatingByNumberOfStars(@RequestParam int stars) {
		return ratingService.getRatingByNumberOfStars(stars);
	}

	@GetMapping("/station/{stationId}")
	// @PreAuthorize("hasRole('USER')")
	@Operation(summary = "Get all ratings for a station")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Ratings retrieved"),
			@ApiResponse(responseCode = "404", description = "No ratings found"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<List<Rating>> getRatingsByStation(@PathVariable Long stationId) {
		List<Rating> ratings = ratingService.getRatingsByStation(stationId);
		return ResponseEntity.ok(ratings);
	}

//	@GetMapping("/station/{stationId}/moderated")
//	// @PreAuthorize("hasRole('USER')")
//	@Operation(summary = "Get moderated ratings for a station")
//	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Moderated ratings retrieved"),
//			@ApiResponse(responseCode = "404", description = "No moderated ratings found"),
//			@ApiResponse(responseCode = "403", description = "Unauthorized") })
//	public ResponseEntity<List<Rating>> getModeratedRatings(@PathVariable Long stationId, @PathVariable boolean moderated) {
//        return ResponseEntity.ok(ratingService.getModeratedRatingsByStation(stationId, moderated));
//    }

	@PutMapping("/{id}/moderate")
	// @PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Moderate a rating")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Rating moderated"),
			@ApiResponse(responseCode = "404", description = "Rating not found"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<Rating> moderateRating(@PathVariable Long id, @RequestParam boolean approved) {
		Rating moderatedRating = ratingService.moderateRating(id, approved);
		return ResponseEntity.ok(moderatedRating);
	}

	@PutMapping("/{id}/points")
	// @PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Add review points")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Points added"),
			@ApiResponse(responseCode = "400", description = "Negative points not allowed"),
			@ApiResponse(responseCode = "404", description = "Rating not found"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<Rating> addReviewPoints(@PathVariable Long id, @RequestParam int points) {
		Rating updatedRating = ratingService.addReviewPoints(id, points);
		return ResponseEntity.ok(updatedRating);
	}

	@PutMapping("/{id}")
	// @PreAuthorize("hasRole('USER')")
	@Operation(summary = "Update a rating")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Rating updated"),
			@ApiResponse(responseCode = "404", description = "Rating not found"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
		Rating updatedRating = ratingService.updateRating(id, rating);
		return ResponseEntity.ok(updatedRating);
	}

	@DeleteMapping("/{id}")
	// @PreAuthorize("hasRole('USER')")
	@Operation(summary = "Delete a rating")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Rating deleted"),
			@ApiResponse(responseCode = "404", description = "Rating not found"),
			@ApiResponse(responseCode = "403", description = "Unauthorized") })
	public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
		ratingService.deleteRating(id);
		return ResponseEntity.noContent().build();
	}
}