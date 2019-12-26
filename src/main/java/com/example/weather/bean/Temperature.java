package com.example.weather.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="Temperature",uniqueConstraints=@UniqueConstraint(columnNames={"cityid", "date"}))
public class Temperature {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="date")
    private Date date;
	
	
	@Column(name="temperature")
    private double temperature;

	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="cityid", nullable = false)
	private City city;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		
		this.city = city;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	


	

	/**
	 * @param id
	 * @param month
	 * @param temperature
	 * @param city
	 */
	
	
	
	
	public Temperature() {}

	/**
	 * @param id
	 * @param date
	 * @param temperature
	 * @param city
	 */
	public Temperature(int id, Date date, double temperature, City city) {
		super();
		this.id = id;
		this.date = date;
		this.temperature = temperature;
		this.city = city;
	}
	
	
	
	
	

}
