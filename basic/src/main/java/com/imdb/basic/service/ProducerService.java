package com.imdb.basic.service;

import com.imdb.basic.dto.ProducerDto;
import com.imdb.basic.exception.ApiRequestException;
import com.imdb.basic.model.Producer;
import com.imdb.basic.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    public void addProducer(ProducerDto producerDto) {

        Producer producer = new Producer();

        producer.setName(producerDto.getName());
        producer.setSex(producerDto.getSex());
        producer.setBio(producerDto.getBio());
        producer.setDob(producerDto.getDob());

        Optional<Producer> verifyProducer = producerRepository.findByname(producerDto.getName());
        if (verifyProducer.isPresent())
            throw new ApiRequestException("Producer is already present");

        producerRepository.save(producer);
    }

    public List<Producer> getProducers() {
        return producerRepository.findAll();
    }
}

