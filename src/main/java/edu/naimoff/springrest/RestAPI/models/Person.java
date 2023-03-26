package edu.naimoff.springrest.RestAPI.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="person")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="person_id")
    private Long id;
    @Column(name="person_name")
    @NotEmpty
    @Size(min=2,max=30,message="should be between 2 and 30 characters")
    private String name;
    @Column(name="person_age")
    @Min(value=0,message="Age should be greater than 0")
    private Integer age;
    @Column(name="person_email")
    @Email
    @NotEmpty(message="Should not be empty")
    private String email;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name="createdWho")
    @javax.validation.constraints.NotEmpty
    private String createdWho;
    public Person(){

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
