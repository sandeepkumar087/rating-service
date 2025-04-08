package com.chargepoint.controller;

import com.chargepoint.rating.controller.StationController;
import com.chargepoint.rating.entity.Station;
import com.chargepoint.rating.service.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StationControllerTest {

    @Mock
    private StationService stationService;

    @InjectMocks
    private StationController stationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStations() {
        when(stationService.getAllStations()).thenReturn(Arrays.asList(new Station(), new Station()));

        List<Station> stations = stationController.getAllStations();
        assertEquals(2, stations.size());
    }

    @Test
    void testSaveStation() {
        Station station = new Station();
        when(stationService.saveStation(station)).thenReturn(station);

        Station result = stationController.saveStation(station);
        assertNotNull(result);
        verify(stationService, times(1)).saveStation(station);
    }
}
