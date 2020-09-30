package com.imdb.basic.controller;

import com.imdb.basic.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@RestController
public class MainController {


    @GetMapping("/")
    public ModelAndView homePage() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/movie")
    public ModelAndView addMovie() throws IOException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addmovie");
        return modelAndView;
    }
}
