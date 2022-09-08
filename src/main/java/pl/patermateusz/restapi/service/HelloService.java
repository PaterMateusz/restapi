package pl.patermateusz.restapi.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello(){
        return "Hello awesome user!";
    }
}
