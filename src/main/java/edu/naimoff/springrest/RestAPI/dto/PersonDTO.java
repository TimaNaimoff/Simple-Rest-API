package edu.naimoff.springrest.RestAPI.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonDTO {
    @NotEmpty
    @Size(min=2,max=30,message="should be between 2 and 30 characters")
    private String name;
    @Min(value=0,message="Age should be greater than 0")
    private Integer age;
    @Email
    @NotEmpty(message="Should not be empty")
    private String email;
}
