package com.imdb.basic.service;

import com.imdb.basic.dto.ActorDto;
import com.imdb.basic.model.Actor;
import com.imdb.basic.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public void addActor(ActorDto actorDto) {
        Actor actor = new Actor();

        actor.setName(actorDto.getName());
        actor.setSex(actorDto.getSex());
        actor.setDob(actorDto.getDob());
        actorRepository.save(actor);

    }

    public List<Actor> getActors() {
        return actorRepository.findAll();
    }
}
