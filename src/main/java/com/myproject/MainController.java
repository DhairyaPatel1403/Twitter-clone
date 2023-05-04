package com.myproject;


import com.myproject.tweets.TweetRepository;
import com.myproject.tweets.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TweetRepository tweetRepository;

    @PostMapping ("")
    public String IndexPage(){
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        // Controller logic here
        List<Tweet> tweets = tweetRepository.findAll();

        Collections.reverse(tweets);

        model.addAttribute("tweets", tweets); // Add tweets to the model

        return "home";
    }

    @GetMapping("/comment")
    public String commentPage() {
        return "comment";
    }


    @GetMapping  ("login")
    public String LoginPage(){
        return "login";
    }

    @GetMapping  ("register")
    public String RegisterPage(){
        return "index";
    }


    @GetMapping  ("addtweet")
    public String AddTweetPage(){
        return "addtweet";
    }

}
