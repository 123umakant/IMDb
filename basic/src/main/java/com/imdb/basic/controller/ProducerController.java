package com.imdb.basic.controller;

import com.imdb.basic.dto.ProducerDto;
import com.imdb.basic.model.Producer;
import com.imdb.basic.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producer")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("get")
    public ResponseEntity<Producer> getProducers(){
      List<Producer> producers = producerService.getProducers();
    return new ResponseEntity(producers,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addProducer(@RequestParam("name") String name, @RequestParam("sex") String sex,
                                      @RequestParam("dob") String dob,@RequestParam("bio") String bio){
        ProducerDto producerDto = new ProducerDto();

        producerDto.setBio(bio);
        producerDto.setDob(dob);
        producerDto.setName(name);
        producerDto.setSex(sex);
        producerService.addProducer(producerDto);
    return new ResponseEntity(HttpStatus.CREATED);
    }

}
