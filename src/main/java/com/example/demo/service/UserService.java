package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
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
