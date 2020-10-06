package com.imdb.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String homePage() {
        return "templates/index.html";
    }

    @GetMapping("/movie")
    public String addMovie() {
        return "templates/addmovie.html";
    }
}
