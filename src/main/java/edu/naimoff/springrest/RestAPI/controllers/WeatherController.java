package edu.naimoff.springrest.RestAPI.controllers;

import edu.naimoff.springrest.RestAPI.dto.PersonDTO;
import edu.naimoff.springrest.RestAPI.dto.WeatherStatusDTO;
import edu.naimoff.springrest.RestAPI.models.WeatherStatus;
import edu.naimoff.springrest.RestAPI.services.WeatherService;
import edu.naimoff.springrest.RestAPI.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
public class WeatherController {
     @Autowired
     private final WeatherService weatherService;
     private final ModelMapper modelMapper;
     public WeatherController(WeatherService weatherService,ModelMapper modelMapper){
         this.weatherService=weatherService;
         this.modelMapper=modelMapper;
     }
     @GetMapping
     public List<WeatherStatusDTO>getAll(){
         return weatherService.index().stream().map(e->convertToWeatherDTO(e)).collect(Collectors.toList());
     }
     @GetMapping("/{id}")
     public WeatherStatusDTO getOne(@PathVariable("id")long id){
         return convertToWeatherDTO(weatherService.findOne(id));
     }
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid WeatherStatusDTO weatherStatusDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage=new StringBuilder();
            List<FieldError>errors=bindingResult.getFieldErrors();
            for(FieldError error:errors){
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMessage.toString());

        }
        weatherService.save(convertToWeather(weatherStatusDTO));
        //Отправляем HTTP-ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }
    private WeatherStatus convertToWeather(WeatherStatusDTO weatherStatus) {

        return modelMapper.map(weatherStatus,WeatherStatus.class);
    }
    private WeatherStatusDTO convertToWeatherDTO(WeatherStatus weatherStatus){
        return modelMapper.map(weatherStatus,WeatherStatusDTO.class);
    }
    @ExceptionHandler
    private ResponseEntity<WeatherErrorResponse>handleException(WeatherNotFoundException notFoundException){
        WeatherErrorResponse response=new WeatherErrorResponse("Person with this id" +
                " was'nt found!",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<WeatherErrorResponse>handleException(WeatherStatusNotCreatedException notFoundException){
        WeatherErrorResponse response=new WeatherErrorResponse(notFoundException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
