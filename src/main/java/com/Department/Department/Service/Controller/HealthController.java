package com.Department.Department.Service.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HealthController {

  @Value("$(welcome.message)")
  private String welcomeMessage;


  private MessageSource messageSource;
public HealthController(MessageSource messageSource){
  this.messageSource=messageSource;
}

    @GetMapping("/")
    public String home(){
        return "WELCOME TO THE DEPARTMENT SERVICE..........";
    }


    @GetMapping("/hello-world-internationalization")
  public String internationalizationMessage(){
Locale locale = LocaleContextHolder.getLocale();
//  return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
  return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }
}
