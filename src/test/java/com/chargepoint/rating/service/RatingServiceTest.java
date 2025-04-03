//package com.chargepoint.rating.service;
//
//import com.chargepoint.rating.entity.Rating;
//import com.chargepoint.rating.repository.RatingRepository;
//import com.chargepoint.rating.exception.RatingNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.Jwt;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class RatingServiceTest {
//
//    @Autowired
//    private RatingService ratingService;
//
//    @MockBean
//    private RatingRepository ratingRepository;
//
//    private Rating rating;
//
//    @BeforeEach
//    void setUp() {
//        rating = new Rating("ST001", "USR001", null, 4, "Good station", false, 0, null, null, null, null);
//        rating.setId("SR-0001");
//
//        // Mock SecurityContext
//        Authentication auth = mock(Authentication.class);
//        when(auth.getName()).thenReturn("USR001");
//        SecurityContextHolder.getContext().setAuthentication(auth);
//    }
//
//    @Test
//    void testCreateRatingSuccess() {
//        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
//        Rating savedRating = ratingService.createRating(rating);
//        assertEquals(4, savedRating.getStarRating());
//        assertEquals("USR001", savedRating.getCreatedBy());
//        assertNotNull(savedRating.getCreatedDate());
//    }
//
//    @Test
//    void testGetRatingsByStationNotFound() {
//        when(ratingRepository.findByStationId("ST001")).thenReturn(List.of());
//        assertThrows(RatingNotFoundException.class, () -> ratingService.getRatingsByStation("ST001"));
//    }
//
//    @Test
//    void testModerateRatingSuccess() {
//        when(ratingRepository.findById("SR-0001")).thenReturn(Optional.of(rating));
//        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
//        Rating moderatedRating = ratingService.moderateRating("SR-0001", true);
//        assertTrue(moderatedRating.isModerated());
//        assertEquals("USR001", moderatedRating.getUpdatedBy());
//    }
//}