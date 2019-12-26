
    
package com.example.weather.exception;


public class TemperatureException extends RuntimeException {


    /**
     * 
     */                      
    private static final long serialVersionUID = 1L;


    public TemperatureException() {
        super();
    }


//    public TemperatureException(String message, Throwable cause, boolean enableSuppression,
//            boolean writableStackTrace) {
//        super(message, cause, enableSuppression, writableStackTrace);
//    }


    public TemperatureException(String message, Throwable cause) {
        super(message, cause);
    }


    public TemperatureException(String message) {
        super(message);
    }


    public TemperatureException(Throwable cause) {
        super(cause);
    }


}
 










