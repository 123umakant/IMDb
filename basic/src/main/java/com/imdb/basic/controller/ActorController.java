package com.imdb.basic.controller;

import com.imdb.basic.dto.ActorDto;
import com.imdb.basic.model.Actor;
import com.imdb.basic.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("get")
    public ResponseEntity<Actor> getActors() {

        List<Actor> actors = actorService.getActors();
        return new ResponseEntity(actors, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addActor(@RequestParam("name") String name, @RequestParam("sex") String sex,
                                   @RequestParam("dob") String dob) {

        ActorDto actorDto =new ActorDto();
        actorDto.setName(name);
        actorDto.setDob(dob);
        actorDto.setSex(sex);

        actorService.addActor(actorDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
