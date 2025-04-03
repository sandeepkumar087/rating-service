package com.chargepoint.rating.service;

import com.chargepoint.rating.entity.Station;

import java.util.List;

public interface StationService {
	List<Station> getAllStations();

	Station saveStation(Station station);
}