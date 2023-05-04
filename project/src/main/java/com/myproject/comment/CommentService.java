package com.myproject.comment;

import com.myproject.tweets.Tweet;
import com.myproject.tweets.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CommentService {

    @Autowired
    private CommentRepository repo;

    @Autowired
    private TweetRepository trepo;


    public void addNew(String text, int id){

        Comment comment = new Comment();

        Tweet tweet = trepo.findById(id);

        comment.setText(text);
        comment.setTweet(tweet);



        Comment savedComment = repo.save(comment);



    }

}
