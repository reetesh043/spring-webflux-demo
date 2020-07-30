package com.reet.spring.webflux.demo.dao;

import com.reet.spring.webflux.demo.model.Tweet;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TweetRepository extends ReactiveMongoRepository<Tweet, Integer> {
    @Query("{ 'userName': ?0 }")
    Flux<Tweet> findByName(final String userName);
}
