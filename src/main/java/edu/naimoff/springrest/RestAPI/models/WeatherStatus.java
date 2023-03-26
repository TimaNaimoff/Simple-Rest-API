package edu.naimoff.springrest.RestAPI.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="weather_status")
public class WeatherStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="temperature")
    private Double temperature;
    @Column(name="damp")
    private Double damp;
    @Column(name="register_time")
    private LocalDateTime registerTime;
}
