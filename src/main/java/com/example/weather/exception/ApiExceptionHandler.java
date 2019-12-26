package com.example.weather.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiException(ApiRequestException e){
		//crete payload containing exception details
		HttpStatus badRequest=HttpStatus.BAD_REQUEST;
		ApiException apiException =new ApiException(
				e.getMessage(),
				
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
				);
		//return response entity
		return new ResponseEntity<>(apiException,badRequest);
	}
    @ExceptionHandler(value = {CityNotFoundException.class})
   	public ResponseEntity<Object> handleCityException(CityNotFoundException e){
   		//crete payload containing exception details
   		HttpStatus notAcceptable=HttpStatus.NOT_FOUND;
   		ApiException apiException =new ApiException(
   				e.getMessage(),
   				HttpStatus.NOT_FOUND,
   				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
   				);
   		//return response entity
   		return new ResponseEntity<>(apiException,notAcceptable);
   	}
    @ExceptionHandler(value = {DataNotFoundException.class})
   	public ResponseEntity<Object> handleDataException(DataNotFoundException e){
   		//crete payload containing exception details
   		HttpStatus internalError=HttpStatus.NOT_FOUND;
   		ApiException apiException =new ApiException(
   				e.getMessage(),
   				
   				HttpStatus.NOT_FOUND,
   				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
   				);
   		//return response entity
   		return new ResponseEntity<>(apiException,internalError);
   	}
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
   	public ResponseEntity<Object> handleDataIntegrityException(DataIntegrityViolationException e){
   		//crete payload containing exception details
   		HttpStatus internalError=HttpStatus.INTERNAL_SERVER_ERROR;
   		ApiException apiException =new ApiException(
   				e.getMessage(),
   				HttpStatus.INTERNAL_SERVER_ERROR,
   				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
   				);
   		//return response entity
   		return new ResponseEntity<>(apiException,internalError);
   	}
    @ExceptionHandler(value = {TemperatureException.class})
   	public ResponseEntity<Object> handleTemperatureException(TemperatureException e){
   		//crete payload containing exception details
   		HttpStatus internalError=HttpStatus.NOT_FOUND;
   		ApiException apiException =new ApiException(
   				e.getMessage(),
   				HttpStatus.NOT_FOUND,
   				ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
   				);
   		//return response entity
   		return new ResponseEntity<>(apiException,internalError);
   	}


}
