package edu.naimoff.springrest.RestAPI.controllers;

import edu.naimoff.springrest.RestAPI.dto.PersonDTO;
import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.services.PeopleService;
import edu.naimoff.springrest.RestAPI.util.PersonErrorResponse;
import edu.naimoff.springrest.RestAPI.util.PersonNotCreatedException;
import edu.naimoff.springrest.RestAPI.util.PersonNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pCont")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;
    @Autowired
    public PeopleController(PeopleService peopleService,ModelMapper modelMapper){
        this.peopleService=peopleService;
        this.modelMapper=modelMapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus>create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
             StringBuilder errorMessage=new StringBuilder();
             List<FieldError>errors=bindingResult.getFieldErrors();
             for(FieldError error:errors){
                 errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                         .append(";");
             }
             throw new PersonNotCreatedException(errorMessage.toString());

        }
        peopleService.save(convertToPerson(personDTO));
        //Отправляем HTTP-ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<PersonDTO> getPeople(){
        return peopleService.getAll()
                .stream().map(e->convertToPersonDTO(e)).collect(Collectors.toList()); //Jackson конвертирует обьекты в JSON
    }

    @GetMapping("/{id}")
    public PersonDTO getOnePeople(@PathVariable("id")long id){
        return convertToPersonDTO(peopleService.getOnePerson(id));  //Jackson также сконвертирует в JSON
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse>handleException(PersonNotFoundException notFoundException){
         PersonErrorResponse response=new PersonErrorResponse("Person with this id" +
                 " was'nt found!",System.currentTimeMillis());
         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse>handleException(PersonNotCreatedException notFoundException){
        PersonErrorResponse response=new PersonErrorResponse(notFoundException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Person convertToPerson(PersonDTO personDTO) {

        return modelMapper.map(personDTO,Person.class);
    }
    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person,PersonDTO.class);
    }
}
