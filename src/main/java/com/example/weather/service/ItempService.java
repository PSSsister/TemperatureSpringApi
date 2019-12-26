package com.example.weather.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.weather.bean.City;

import com.example.weather.bean.Temperature;
import com.example.weather.exception.TemperatureException;




public interface ItempService {
	
	City createCity(City city);
    Temperature createTemperature(Temperature temp);
	List<City> updateCity(Integer id,City city);
	Temperature updateTemperature(String cityName,Date date,Temperature temp);
	String deleteByCityName (String city);
	List<City> getAllCity();
	List<Temperature> getTemperature();
	List<Temperature> findByCity(String cityName);
	Temperature findByCityAndDate(String cityName,Date date);
	List<Temperature> findByDate(Date date);
	Map<String, Double> getAverage(int year,String cityName) throws TemperatureException;
	List<Temperature> getTemperatureByCityWithUnit(String cityName,String unit);

    
}



