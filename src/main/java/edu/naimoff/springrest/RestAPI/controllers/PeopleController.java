package edu.naimoff.springrest.RestAPI.controllers;

import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pCont")
public class PeopleController {
    private final PeopleService peopleService;
    @Autowired
    public PeopleController(PeopleService peopleService){
        this.peopleService=peopleService;
    }
    @GetMapping()
    public List<Person> getPeople(){
        return peopleService.getAll(); //Jackson конвертирует обьекты в JSON
    }
    @GetMapping("/{id}")
    public Person getOnePeople(@PathVariable("id")long id){
        return peopleService.getOnePerson(id);  //Jackson также сконвертирует в JSON
    }
}
