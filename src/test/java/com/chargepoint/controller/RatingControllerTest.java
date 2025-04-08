package com.chargepoint.controller;

import com.chargepoint.rating.controller.RatingController;
import com.chargepoint.rating.entity.Rating;
import com.chargepoint.rating.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @InjectMocks
    private RatingController ratingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRating() {
        Rating rating = new Rating();
        when(ratingService.createRating(any(Rating.class))).thenReturn(rating);

        ResponseEntity<Rating> response = ratingController.createRating(rating);
        assertEquals(200, response.getStatusCodeValue());
        verify(ratingService, times(1)).createRating(rating);
    }

    @Test
    void testGetRating() {
        Rating rating = new Rating();
        rating.setId(1L);
        when(ratingService.getRating(1L)).thenReturn(Optional.of(rating));

        ResponseEntity<Rating> response = ratingController.getRating(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void testGetAllRatings() {
        when(ratingService.getAllRatings()).thenReturn(Arrays.asList(new Rating(), new Rating()));

        List<Rating> ratings = ratingController.getAllRatings();
        assertEquals(2, ratings.size());
    }

    @Test
    void testGetRatingByNumberOfStars() {
        when(ratingService.getRatingByNumberOfStars(5)).thenReturn(Arrays.asList(new Rating()));

        List<Rating> ratings = ratingController.getRatingByNumberOfStars(5);
        assertEquals(1, ratings.size());
    }

    @Test
    void testGetRatingsByStation() {
        when(ratingService.getRatingsByStation(101L)).thenReturn(Arrays.asList(new Rating()));

        ResponseEntity<List<Rating>> response = ratingController.getRatingsByStation(101L);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testGetAverageRating() {
        when(ratingService.getAverageRatingForStation(101L)).thenReturn(4.2);

        ResponseEntity<Double> response = ratingController.getAverageRating(101L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(4.2, response.getBody());
    }
}
