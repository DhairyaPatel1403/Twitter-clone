package com.myproject.tweets;


import com.myproject.comment.Comment;
import com.myproject.comment.CommentRepository;
import com.myproject.user.User;
import com.myproject.user.UserRepository;
import com.myproject.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
public class TweetController {

    @Autowired
    private TweetService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TweetRepository repo;

    @Autowired
    private CommentRepository crepo;

    @PostMapping("/addtweet")
    public String addUser(@RequestParam String tweet, @SessionAttribute("name") String name, HttpSession session) {


        User user = userRepository.findByName(name);


        service.addNew(tweet, user);

        return "redirect:/home";
    }


    @PostMapping("/comment")
    public String addComment(@RequestParam int id, Model model, HttpSession session) {

        Tweet tweet = repo.findById(id);

        session.setAttribute("selectedtweetId", id); // Store the id in the session

        service.likeIt(id);

        System.out.println("Here "+id);


        List<Comment> comments = crepo.findByTweetId(id);

        for (Comment comment : comments) {
            System.out.println("Comment ID: " + comment.getId());
            System.out.println("Comment Text: " + comment.getText());
            // Add more properties to print as needed
        }



        session.setAttribute("commentstolist", comments);

        return "redirect:/comment";
    }




    @PostMapping("/deletetweet")
    public String checkUser(@RequestParam int id) {

        service.deletet(id);

        return "redirect:/home";
    }




}
