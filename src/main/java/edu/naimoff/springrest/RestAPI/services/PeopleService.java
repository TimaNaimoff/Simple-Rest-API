package edu.naimoff.springrest.RestAPI.services;

import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.repositories.PeopleRepository;
import edu.naimoff.springrest.RestAPI.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }
    public Person getOnePerson(long id){
         Optional<Person> person=peopleRepository.findOne(id);
         return person.orElseThrow(PersonNotFoundException::new);
    }
    public List<Person> getAll(){
        return peopleRepository.findAll();
    }
    public void save(Person person){
        peopleRepository.save(person);
    }
    public void delete(Person person){
        peopleRepository.delete(person);
    }
    public void update(long id,Person person){
        person.setId(id);
        peopleRepository.save(person);
    }


}
