package com.myproject;


import com.myproject.user.User;
import com.myproject.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(String name, String email, int id, String passw) {
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setId(id);
        user.setPassword(passw);

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();

        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);


    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();

        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users){
            System.out.println(user);
        }
    }


    @Test
    public void testUpdate(){

        Optional<User> optionalUser = repo.findById(5);

        User user = optionalUser.get();

        user.setPassword("newPassword");

        repo.save(user);

        User updatedUser = repo.findById(5).get();

        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("newPassword");
    }


    @Test
    public void testGet(){
        Optional<User> optionalUser = repo.findById(3);

        Assertions.assertThat(optionalUser).isPresent();

        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        repo.deleteById(5);
        Optional<User> optionalUser = repo.findById(5);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
