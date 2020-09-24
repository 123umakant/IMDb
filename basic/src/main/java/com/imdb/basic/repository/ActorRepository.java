package com.imdb.basic.repository;

import com.imdb.basic.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository  extends JpaRepository<Actor,Integer> {

    Actor findByname(String name);
}
