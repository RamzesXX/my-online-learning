package com.khanchych.udemy.javaindepth.thrillio.dao;

import com.khanchych.udemy.javaindepth.thrillio.DataStore;
import com.khanchych.udemy.javaindepth.thrillio.entities.User;

public class UserDao {
    public User[] getUsers() {
        return DataStore.getUsers();
    }
}
