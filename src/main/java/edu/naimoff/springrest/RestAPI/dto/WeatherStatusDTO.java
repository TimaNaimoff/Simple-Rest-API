package edu.naimoff.springrest.RestAPI.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class WeatherStatusDTO {
    @Column(name="temperature")
    private Double temperature;
    @Column(name="damp")
    private Double damp;

}
