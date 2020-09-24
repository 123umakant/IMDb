package com.imdb.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String homePage(){
        System.out.println("inside home");
        return "index";
    }
    @GetMapping("/movie")
    public String addMovie(){
        return "addmovie";
    }
}
