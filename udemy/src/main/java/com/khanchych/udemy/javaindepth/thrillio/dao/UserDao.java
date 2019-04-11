package com.khanchych.udemy.javaindepth.thrillio.dao;

import com.khanchych.udemy.javaindepth.thrillio.DataStore;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;

import java.util.List;

public class UserDao {
    public List<User> getUsers() {
        return DataStore.getUsers();
    }
}
