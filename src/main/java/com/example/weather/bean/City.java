package com.example.weather.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "city",uniqueConstraints={@UniqueConstraint(columnNames= {"latitude","longitude"})})
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private int id;
	@Column(name="city_name",unique=true)
	private String cityName;
	@Column(name="latitude")
	private double latitude;
	@Column(name="longitude")
	
	private double longitude;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	

}
