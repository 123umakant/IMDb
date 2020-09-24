package com.imdb.basic.repository;

import com.imdb.basic.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer,Integer> {
    Producer findByname(String producer);
}
