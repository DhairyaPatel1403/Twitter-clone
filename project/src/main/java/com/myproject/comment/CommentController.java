package com.myproject.comment;


import com.myproject.tweets.Tweet;
import com.myproject.tweets.TweetRepository;
import com.myproject.tweets.TweetService;
import com.myproject.user.User;
import com.myproject.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CommentController {

    @Autowired
    private CommentService service;
    @Autowired
    private CommentRepository repo;
    @Autowired
    private TweetRepository tweetrepo;

    @PostMapping("/commentsub")
    public String addUser(@RequestParam String comment, HttpSession session) {

        int id = (int) session.getAttribute("selectedtweetId");

        service.addNew(comment, id);

        return "redirect:/home";
    }


}
