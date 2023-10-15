package com.Department.Department.Service.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @Value("$(welcome.message)")
  private String welcomeMessage;
    @GetMapping("/")
    public String home(){
        return "WELCOME TO THE DEPARTMENT SERVICE..........";

    }
}
