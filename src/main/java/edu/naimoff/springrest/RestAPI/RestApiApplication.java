package edu.naimoff.springrest.RestAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = { "edu.naimoff.springrest.RestAPI.*" })
//@EntityScan("edu.naimoff.springrest.RestAPI.models.*")
@SpringBootApplication
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
