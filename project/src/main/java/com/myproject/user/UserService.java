package com.myproject.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository repo;



    public void addNew(String name, String email, String passw) {
        User user = new User();

        user.setName(name);
        user.setEmail(email);

        user.setPassword(passw);

        User savedUser = repo.save(user);

        System.out.println(user);


    }

    public boolean check(String email, String password){
        Iterable<User> users = repo.findAll();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Matched");
                return true; // return true if email and password match
            }
        }

        return false;
    }

    public String getNameByEmail(String email){

        User user = repo.findByEmail(email);

        return user.getName();
    }




}
