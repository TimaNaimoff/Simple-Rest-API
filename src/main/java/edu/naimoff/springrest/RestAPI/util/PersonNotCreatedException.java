package edu.naimoff.springrest.RestAPI.util;

public class PersonNotCreatedException extends RuntimeException{
    public PersonNotCreatedException(String errorMessage){
        super(errorMessage);
    }
}
