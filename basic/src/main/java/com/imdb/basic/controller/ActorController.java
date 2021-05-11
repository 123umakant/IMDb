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

    @GetMapping("/actors")
    public ResponseEntity<Actor> getActors() {

        List<Actor> actors = actorService.getActors();
        return new ResponseEntity(actors, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity addActor(@RequestBody ActorDto actorDto) {

        actorService.addActor(actorDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
