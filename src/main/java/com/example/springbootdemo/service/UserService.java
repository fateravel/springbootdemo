package com.example.springbootdemo.service;

import com.example.springbootdemo.dao.UserDao;
import com.example.springbootdemo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
