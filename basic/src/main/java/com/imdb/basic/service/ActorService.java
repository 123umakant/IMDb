package com.imdb.basic.service;

import com.imdb.basic.dto.ActorDto;
import com.imdb.basic.exception.ApiRequestException;
import com.imdb.basic.model.Actor;
import com.imdb.basic.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public void addActor(ActorDto actorDto) {

        Actor actor = new Actor();
        actor.setName(actorDto.getName());
        actor.setSex(actorDto.getSex());
        actor.setDob(actorDto.getDob());

        Optional<Actor> verifyActor = actorRepository.findByname(actorDto.getName());

        if (verifyActor.isPresent())
            throw new ApiRequestException("Actor is Already Present");

        actorRepository.save(actor);
    }

    public List<Actor> getActors() {
        return actorRepository.findAll();
    }
}
