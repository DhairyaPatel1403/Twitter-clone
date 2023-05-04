package com.myproject.tweets;

import com.myproject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TweetService {

    @Autowired
    private TweetRepository repo;

    public void addNew(String text, User user) {
        Tweet tweet = new Tweet();

        tweet.setText(text);
        tweet.setUser(user);

        Tweet savedTweet = repo.save(tweet);

        System.out.println(savedTweet);


    }


    public void likeIt(int id){
        Optional<Tweet> tweet = Optional.ofNullable(repo.findById(id));

        int likes = tweet.get().getLikes();

        likes += 1;

        tweet.get().setLikes(likes);

        repo.save(tweet.get());


    }

    public void deletet(int id){

        repo.deleteById(id);

    }
}
