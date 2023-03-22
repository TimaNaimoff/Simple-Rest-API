package edu.naimoff.springrest.RestAPI.repositories;

import edu.naimoff.springrest.RestAPI.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.id=:personId")
    Optional<Person> findOne(@Param("personId")long id);
}
