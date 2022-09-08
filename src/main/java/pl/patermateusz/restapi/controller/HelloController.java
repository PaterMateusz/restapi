package pl.patermateusz.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.patermateusz.restapi.service.HelloService;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/")
    public String hello(){
        return helloService.hello();
    }
}
