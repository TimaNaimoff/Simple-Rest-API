package edu.naimoff.springrest.RestAPI.controllers;

import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.services.PeopleService;
import edu.naimoff.springrest.RestAPI.util.PersonErrorResponse;
import edu.naimoff.springrest.RestAPI.util.PersonNotCreatedException;
import edu.naimoff.springrest.RestAPI.util.PersonNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/pCont")
public class PeopleController {
    private final PeopleService peopleService;
    @Autowired
    public PeopleController(PeopleService peopleService){
        this.peopleService=peopleService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus>create(@RequestBody @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
             StringBuilder errorMessage=new StringBuilder();
             List<FieldError>errors=bindingResult.getFieldErrors();
             for(FieldError error:errors){
                 errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                         .append(";");
             }
             throw new PersonNotCreatedException(errorMessage.toString());

        }
        peopleService.save(person);
        //Отправляем HTTP-ответ с пустым телом и со статусом 200
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping()
    public List<Person> getPeople(){
        return peopleService.getAll(); //Jackson конвертирует обьекты в JSON
    }
    @GetMapping("/{id}")
    public Person getOnePeople(@PathVariable("id")long id){
        return peopleService.getOnePerson(id);  //Jackson также сконвертирует в JSON
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
}
