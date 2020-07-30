package com.reet.spring.webflux.demo.service;

import com.reet.spring.webflux.demo.dao.TweetRepository;
import com.reet.spring.webflux.demo.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TweetService implements ITweetService {

    @Autowired
    TweetRepository tweetRepo;

    public void create(Tweet e) {
        tweetRepo.save(e).subscribe();
    }

    public Mono<Tweet> findById(Integer id) {
        return tweetRepo.findById(id);
    }

    public Flux<Tweet> findByName(String name) {
        return tweetRepo.findByName(name);
    }

    public Flux<Tweet> findAll() {
        return tweetRepo.findAll();
    }

    public Mono<Tweet> update(Tweet e) {
        return tweetRepo.save(e);
    }

    public Mono<Void> delete(Integer id) {
        return tweetRepo.deleteById(id);
    }

}
