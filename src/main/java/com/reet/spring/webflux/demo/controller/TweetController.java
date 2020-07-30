package com.reet.spring.webflux.demo.controller;

import com.reet.spring.webflux.demo.model.Tweet;
import com.reet.spring.webflux.demo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.time.Duration;
import java.util.List;

@RestController
public class TweetController {

    @Autowired
    private ConfigurationBuilder cb;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TwitterFactory tf;

    @RequestMapping(value = {"/save"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void saveTweetsToDB() {
        Twitter twitter = tf.getInstance();

        try {
            Query query = new Query("peek_a_joy");
            query.setCount(100);
            QueryResult result;
            result = twitter.search(query);
            List<Status> tweets = result.getTweets();
            for (Status status : tweets) {
                Tweet tweet = new Tweet();
                tweet.setTweetId((int) status.getId());
                tweet.setTweetText(status.getText());
                tweet.setUserName(status.getUser().getName());
                tweetService.create(tweet);
            }

        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    public Flux<Tweet> find() {
        Flux<Tweet> e = tweetService.findAll();
        return e.delaySequence(Duration.ofSeconds(2)).log();
    }
}
