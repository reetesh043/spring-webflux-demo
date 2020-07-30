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
        cb.setOAuthConsumerKey("G7ONPMJ0FzK1FERlg62Qgf8ke");
        cb.setOAuthConsumerSecret("JSHZxLHwr1S8aOBH1RGHkZ7AYpwEUj9VbmwWFW2okTrfgvimBc");
        cb.setOAuthAccessToken("170968528-IKPw1FyTLilguVKEC2uBAxHZN3Ru10fDO02Qkm8Q");
        cb.setOAuthAccessTokenSecret("fQT28vH7aM5sPvyok4Eliyc4zSwxP7PFSZvYBUVu7Kwec");
        return cb;
    }

    @Bean
    public TwitterFactory postConstruct() {
        TwitterFactory tf = new TwitterFactory(build().build());
        return tf;

    }
}