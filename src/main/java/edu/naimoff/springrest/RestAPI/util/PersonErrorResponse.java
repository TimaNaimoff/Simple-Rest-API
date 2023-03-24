package edu.naimoff.springrest.RestAPI.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonErrorResponse{
    private String name;
    private long timestamp;
}
