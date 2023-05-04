package com.myproject.tweets;


import com.myproject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {
    Tweet findById(int id);
}
