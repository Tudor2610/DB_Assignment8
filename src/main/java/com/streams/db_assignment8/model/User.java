package com.streams.db_assignment8.model;


import com.github.javafaker.Faker;
import lombok.Data;


@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public User(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}
