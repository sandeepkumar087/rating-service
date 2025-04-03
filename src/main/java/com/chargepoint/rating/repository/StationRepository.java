package com.chargepoint.rating.repository;

import com.chargepoint.rating.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> { 
	
}