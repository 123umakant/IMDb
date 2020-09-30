package com.imdb.basic.repository;

import com.imdb.basic.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProducerRepository extends JpaRepository<Producer,Integer> {
   Optional<Producer> findByname(String producer);
}
