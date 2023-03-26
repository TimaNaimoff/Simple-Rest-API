package edu.naimoff.springrest.RestAPI.repositories;

import edu.naimoff.springrest.RestAPI.dto.WeatherStatusDTO;
import edu.naimoff.springrest.RestAPI.models.Person;
import edu.naimoff.springrest.RestAPI.models.WeatherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherStatus,Long> {
    @Query("SELECT w FROM WeatherStatus w WHERE w.id=:weatherId")
    Optional<WeatherStatus> findOne(@Param("weatherId")long id);

}
