package com.reet.spring.webflux.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootWebfluxDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxDemoApp.class, args);
    }

    @Bean
    public ConfigurationBuilder build() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("xxxxxxx");
        cb.setOAuthConsumerSecret("xxxxxxx");
        cb.setOAuthAccessToken("xxxxxxxxxx");
        cb.setOAuthAccessTokenSecret("xxxxxxxxxxx");
        return cb;
    }

    @Bean
    public TwitterFactory postConstruct() {
        TwitterFactory tf = new TwitterFactory(build().build());
        return tf;

    }
}
