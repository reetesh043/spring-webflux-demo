package com.reet.spring.webflux.demo.service;

import com.reet.spring.webflux.demo.model.Tweet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITweetService {
    void create(Tweet e);

    Mono<Tweet> findById(Integer id);

    Flux<Tweet> findByName(String name);

    Flux<Tweet> findAll();

    Mono<Tweet> update(Tweet e);

    Mono<Void> delete(Integer id);
}
