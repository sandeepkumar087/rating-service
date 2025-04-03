package com.chargepoint.rating.controller;

import com.chargepoint.rating.entity.Station;
import com.chargepoint.rating.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/allStations")
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @PostMapping("/createStations")
    public Station saveStation(@RequestBody Station station) {
        return stationService.saveStation(station);
    }
}