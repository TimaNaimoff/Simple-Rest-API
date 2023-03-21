package edu.naimoff.springrest.RestAPI.repositories;

import edu.naimoff.springrest.RestAPI.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
}
