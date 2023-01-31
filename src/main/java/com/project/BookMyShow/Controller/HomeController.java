package com.project.BookMyShow.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    public String index(){
        return "Greetings from BookMyShow";
    }

    @GetMapping("/error")
    public String index1(){
        return "Greetings from BookMyShow";
    }
}
