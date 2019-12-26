package com.example.weather.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weather.bean.City;
import com.example.weather.bean.Temperature;

import com.example.weather.exception.CityNotFoundException;
import com.example.weather.exception.DataIntegrityViolationException;
import com.example.weather.exception.DataNotFoundException;
import com.example.weather.exception.TemperatureException;
import com.example.weather.service.ItempService;

@RestController
@RequestMapping
public class TemperatureController {
	
	@Autowired
	ItempService tempservice;
	
	@PostMapping(value = "/city")
	public City createCity(@RequestBody final City city) {
		try {
		return tempservice.createCity(city);
		}
		catch(Exception e)
		{
			throw new DataIntegrityViolationException("Data already exist");
		}
	}
	
	@PostMapping(value = "/temp")
	public Temperature createTemperature(@RequestBody final Temperature temp) {
		try {
		return tempservice.createTemperature(temp);
		}
		catch(Exception e) {
			throw new DataIntegrityViolationException("Data already exist");
		}
	}
	

	//updating
	@PutMapping(value="/city/{id}")
	public List <City> updatecity(@PathVariable int id, @RequestBody City city) {
		try {
		return tempservice.updateCity(id, city);
		}
		catch(Exception ex)
		{
			throw new CityNotFoundException("Please enter valid city");
		}
	}
	
	//updating
	@PutMapping(value="/temp")
	public Temperature updateTemperature(@RequestParam String cityName,@RequestParam Date date, @RequestBody Temperature temp) {
		try {
		return tempservice.updateTemperature(cityName,date, temp);
		}
		catch(CityNotFoundException e) {
			e.printStackTrace();
			return null;}
		catch(Exception e) {
			throw new DataNotFoundException("Data not found");
		}
		
	}
	
	//view All
	@GetMapping("/city")
	public List <City> getAllTheCity() 
	{
		return tempservice.getAllCity();
	}
	
	@GetMapping("/temp")
	
	public List<Temperature> getAllTemperature()
	{
		return tempservice.getTemperature();
	}
	@GetMapping("/tempByCity")
	public List<Temperature> getTemperatureByCity(@RequestParam ("cityName")  String cityName) {
         return tempservice.findByCity(cityName);
     }

	@GetMapping("/conversion")
	public List<Temperature> getTemperatureByCity(@RequestParam ("cityName")  String cityName,@RequestParam("unit") String unit) {
		
         try {
			return tempservice.getTemperatureByCityWithUnit(cityName, unit);
		} catch (TemperatureException e) {
	
			e.printStackTrace();
		}
		return null;
		
     }
	
	@GetMapping("/tempByDate")
	public List <Temperature> getTemperatureByDate(@RequestParam("date") Date date) {

	    return tempservice.findByDate(date);
	 
	
	}
	@GetMapping("/tempByCityAndDate")
	public Temperature getByCityAndDate(@RequestParam("cityName") String cityName,@RequestParam("date") Date date) {
		return tempservice.findByCityAndDate(cityName, date);
	}

//Delete 
   @DeleteMapping("/temp")
   public String deleteProduct(@RequestParam String cityName) {
       return this.tempservice.deleteByCityName(cityName);
   }
  
      @GetMapping(value="/tempAvg")
       public Map<String, Double> fetchAverage(@RequestParam("year") int year,@RequestParam("cityName") String cityName) throws TemperatureException{
        return tempservice.getAverage(year, cityName);
          
       }
   
}
 
