package com.streams.db_assignment8.model;

import com.github.javafaker.Faker;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Data
public class UserStream {
    private List<User> userList ;
    private Stream<User> userStream ;

    public UserStream(){
        userList = new ArrayList<>();
        Random r = new Random();
        Faker f = new Faker();
        for(int i = 1; i <= 100; i++){
            userList.add(new User(i, new Faker().name().fullName(), r.nextInt(1,100)));
        }
        userStream = userList.stream();
    }
}
