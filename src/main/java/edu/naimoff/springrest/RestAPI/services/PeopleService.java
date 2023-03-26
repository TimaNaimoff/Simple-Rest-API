package edu.naimoff.springrest.RestAPI.services;

import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.repositories.PeopleRepository;
import edu.naimoff.springrest.RestAPI.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional
    public void save(Person person){
        enrichPerson(person);
        peopleRepository.save(person);
    }
    public void delete(Person person){
        peopleRepository.delete(person);
    }
    public void update(long id,Person person){
        person.setId(id);
        enrichPerson(person);
        peopleRepository.save(person);
    }
    private void enrichPerson(Person person){
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN2");
    }

}
