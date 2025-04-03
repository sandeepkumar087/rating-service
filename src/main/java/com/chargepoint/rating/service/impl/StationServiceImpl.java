package com.chargepoint.rating.service.impl;

import com.chargepoint.rating.entity.Station;
import com.chargepoint.rating.repository.StationRepository;
import com.chargepoint.rating.service.StationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }
}