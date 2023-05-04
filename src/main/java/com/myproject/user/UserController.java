package com.myproject.user;

import org.springframework.ui.Model;
import com.myproject.tweets.Tweet;
import com.myproject.tweets.TweetRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
    public class UserController {
        @Autowired
        private UserService service;

        @Autowired
        private TweetRepository tweetRepository;

        @PostMapping("/register")
        public String addUser(@RequestParam String name, Model model , @RequestParam String email, @RequestParam String password, HttpSession session) {
            service.addNew(name, email, password);
            session.setAttribute("name", name);

            List<Tweet> tweets = tweetRepository.findAll();

            model.addAttribute("tweets", tweets); // Add tweets to the model


            return "redirect:/home";
        }

        @PostMapping("/home")
        public String checkUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
            boolean cond = service.check(email, password);

            String name = service.getNameByEmail(email);

            session.setAttribute("name", name);

            List<Tweet> tweets = tweetRepository.findAll();

            model.addAttribute("tweets", tweets); // Add tweets to the model

            if(cond){
                return "redirect:/home";
            }
            return "redirect:/register";
        }




    @PostMapping("/logout")
    public String logOut(HttpSession session) {

        session.invalidate();

        return "redirect:/register";
    }






    }

