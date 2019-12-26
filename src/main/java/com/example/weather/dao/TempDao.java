package com.example.weather.dao;



import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.weather.bean.Temperature;
@Repository
public interface TempDao extends JpaRepository<Temperature, Integer>{
	
	@Query("Select temp from Temperature temp where temp.city.cityName= ?1")
	List<Temperature> findByCity(String city);
	
	@Query("Select temp from Temperature temp where temp.city.cityName = :cityName and temp.date =:date")
	Temperature findByCityAndDate(String cityName,Date date);
	
	@Query("Select temp from Temperature temp where temp.date = :date")
	List<Temperature> findByDate(Date date);
	
	
	@Query("select avg(t.temperature) from Temperature t where t.city.cityName = :city AND year(t.date) = :year")
    Double getAverage(int year,String city);
}
