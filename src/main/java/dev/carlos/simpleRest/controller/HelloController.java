package dev.carlos.simpleRest.controller;

import dev.carlos.simpleRest.repository.dao.dto.EmailDataDAO;
import dev.carlos.simpleRest.repository.dao.dto.HelloDTO;
import dev.carlos.simpleRest.service.HelloService;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

  @Inject
  HelloService helloService;

  public HelloController(HelloService helloService){
    this.helloService=helloService;
  }

  @GetMapping("/hello")
  public HelloDTO getHello(){
    return helloService.getHello();
  }

  @RequestMapping(
      value = "/hello",
      method = RequestMethod.POST,
      produces = "application/json"
  )
  public String postHello(@RequestBody List<EmailDataDAO> contacts) {return helloService.postHello(contacts);}
}
