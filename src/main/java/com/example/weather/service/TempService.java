package com.example.weather.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.weather.bean.City;
import com.example.weather.bean.Temperature;
import com.example.weather.dao.CityRepo;
import com.example.weather.dao.TempDao;
import com.example.weather.exception.CityNotFoundException;
import com.example.weather.exception.DataIntegrityViolationException;
import com.example.weather.exception.DataNotFoundException;
import com.example.weather.exception.TemperatureException;

@Service
public class TempService implements ItempService{

   @Autowired
   TempDao tempDao;
	
	@Autowired
	CityRepo cityDao;

	@Override
	public City createCity(City city) {
	
		boolean result=isCityNameIsValid(city.getCityName()); 
		boolean result2=validateLongitudeAndLattitude(city.getLongitude(),city.getLatitude());
		if(result ==true && result2 ==true) {
	    	return cityDao.save(city);
		}
	    return city;
		}
		


	@Override
	public Temperature createTemperature(Temperature temp) {
		City cityObj = cityDao.getCity(temp.getCity().getCityName());
		if(cityObj == null) {
			throw new CityNotFoundException("Cannot create temperature for given city.");
		}
		temp.setCity(cityObj);
		return tempDao.save(temp);
	}

	@Override
	public List<City> getAllCity() {
		
		return cityDao.findAll() ;
	}

	@Override
	public List<City> updateCity(Integer id, City city) {
		Optional<City> optional=cityDao.findById(id);
		if(optional.isPresent())
		{
			City od=optional.get();
			od.setCityName(city.getCityName());
			od.setLatitude(city.getLatitude());
			isCityNameIsValid(od.getCityName());
			od.setLongitude(city.getLongitude());
			cityDao.save(od);
		}
		else
		{
			throw new DataNotFoundException("temperature with Id "+ id +" is not  existing please enter an valid id ");	
		}
		return getAllCity();
	}

	@Override
	public Temperature updateTemperature(String cityName,Date date, Temperature temp) {
		
		  List<Temperature> result=tempDao.findByCity(cityName);
	      Temperature od=tempDao.findByCityAndDate(cityName, date);
	      od.setDate(temp.getDate());
	      od.setTemperature(temp.getTemperature());
	      return tempDao.save(od);
	   }

	@Override
	public List<Temperature> getTemperature() {
     return tempDao.findAll();
	}

	

	@Override
	public List<Temperature> getTemperatureByCityWithUnit(String city,String unit)  {
		   boolean result=isCityNameIsValid(city);
		   List<Temperature>list =tempDao.findByCity(city);
		   if(list.isEmpty())
		   {
			   throw new DataNotFoundException("Data Not Found Exception");
		   }
		   else {
				if(unit==null) {
					return list;
				}
				list = convertData(list, unit);
				return list;
			}
		  
   }

	@Override
	public Temperature findByCityAndDate(String city, Date date) {
        Temperature t=tempDao.findByCityAndDate(city, date);
        if(t ==null) {
             throw new DataNotFoundException("Data Not Found Exception");
        }
		return t;
	}

	@Override
	public List<Temperature> findByDate(Date date) {
		List<Temperature> list=tempDao.findByDate(date);
		if(list.isEmpty()) {
			throw new DataNotFoundException("Data Not Found Exception");
		}
		return list;
	}

	@Override
	public String deleteByCityName(String city) {
		boolean result=isCityNameIsValid(city);
		if(result == true) {
		int id= cityDao.findByCityName(city).getId();
		cityDao.deleteById(id);
		
		if(cityDao.existsById(id)) {
			return "City not deleted Successfully";
		}
		
		else {
			return "City deleted Successfully";
		}
		}
		return city;
	}

	public boolean isCityNameIsValid(String city) throws CityNotFoundException {
	if (city.equalsIgnoreCase("Mumbai") || city.equalsIgnoreCase("Banglore") || city.equalsIgnoreCase("Hyderabad") ||city.equalsIgnoreCase("UttarPradesh")||city.equalsIgnoreCase("Kolkata")) {
		return true;
	}
	else
	{
		throw new CityNotFoundException("Please enter valid city Name");
	}
	
	}
	private boolean validateLongitudeAndLattitude(Double longitude, Double latitude) {
        City city = cityDao.findByLongitudeAndLattitude(longitude,latitude);
        if(city==null) {
            return true;
        }
        else {
            throw new DataIntegrityViolationException("Longitude and Lattitude value Not be same");
        }
	}
    
       @Override
    public Map<String,Double> getAverage(int year, String cityName) throws TemperatureException{
        Map<String,Double> result = new HashMap<String,Double>();
        Double average = tempDao.getAverage(year, cityName);
        result.put("Average", average);
        if(average==null) {
        	throw new DataNotFoundException("Data Not Found");
        }
        return result;
       }




   
    
private List<Temperature> convertData(List<Temperature> data, String unit){
	List<Temperature> new_data = new ArrayList<Temperature>(data.size());
	if(unit.equalsIgnoreCase("celsius")) {
		return data;
	}
	else if(unit.equalsIgnoreCase("Fahreneit")) {
		for (Temperature temp : data) {
			Temperature entity = new Temperature();
			double fahrenheit =  (9 * (temp.getTemperature() / 5) + 32);
			entity.setId(temp.getId());
			entity.setCity(temp.getCity());
		    entity.setDate(temp.getDate());
			entity.setTemperature(fahrenheit);			
			new_data.add(entity);
		}
	}
	else if(unit.equalsIgnoreCase("kelvin")) {
		for (Temperature temp : data) {
			Temperature entity = new Temperature();
			double kelvin =  temp.getTemperature() + 273.45;
			
			entity.setId(temp.getId());
			entity.setCity(temp.getCity());
			entity.setDate(temp.getDate());
			entity.setTemperature(kelvin);				
			new_data.add(entity);
		}
	}
	else {
		throw new TemperatureException("Unit is not valid");
    }
	return new_data;
}

@Override
public List<Temperature> findByCity(String cityName) {
           boolean result=isCityNameIsValid(cityName);
		   List<Temperature> list =tempDao.findByCity(cityName);
		   if(list.isEmpty())
		   {
			   throw new DataNotFoundException("Data Not Found Exception");
		   }
		  return list;
   }





	

	
}
