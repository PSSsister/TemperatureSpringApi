package com.example.weather.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.weather.bean.City;

@Repository
public interface CityRepo extends JpaRepository<City, Integer> {
		
	@Query(value = "SELECT c from City c where c.cityName = :cityName")
	City getCity(String cityName);

	City findByCityName(String city);
	@Query(value = "SELECT c from City c where c.longitude = :longitude and c.latitude = :latitude")
	City findByLongitudeAndLattitude(Double longitude, Double latitude); 
	

	
}
