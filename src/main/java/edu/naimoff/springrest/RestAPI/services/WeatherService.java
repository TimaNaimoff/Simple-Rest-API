package edu.naimoff.springrest.RestAPI.services;

import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.models.WeatherStatus;
import edu.naimoff.springrest.RestAPI.repositories.WeatherRepository;
import edu.naimoff.springrest.RestAPI.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;
    @Transactional
    public void save(WeatherStatus weatherStatus){
         enrichWeather(weatherStatus);
         weatherRepository.save(weatherStatus);
    }
    @Transactional
    public void delete(WeatherStatus weatherStatus){
        weatherRepository.delete(weatherStatus);
    }
    public List<WeatherStatus> index(){
        return weatherRepository.findAll();
    }
    public WeatherStatus findOne(long id){
        Optional<WeatherStatus> weatherPerson=weatherRepository.findOne(id);
        return weatherPerson.orElseThrow(PersonNotFoundException::new);
    }
    private void enrichWeather(WeatherStatus weatherStatus){
         weatherStatus.setRegisterTime(LocalDateTime.now());
    }
}
