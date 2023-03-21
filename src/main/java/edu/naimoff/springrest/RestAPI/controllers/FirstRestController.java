package edu.naimoff.springrest.RestAPI.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FirstRestController {
    @GetMapping("sayWolderus")
    public String sayHello(){
        return "Hello worlderus";
    }
}
