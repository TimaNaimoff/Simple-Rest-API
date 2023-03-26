package edu.naimoff.springrest.RestAPI.util;

public class WeatherStatusNotCreatedException extends RuntimeException{
    public WeatherStatusNotCreatedException(String errorReport){
        super(errorReport);
    }
}
