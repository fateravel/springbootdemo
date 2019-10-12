package com.example.service.impl;

import com.example.controller.UserController;
import com.example.dao.RedisDao;
import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pengsong
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserController userController;

    private final UserDao userDao;

    private final RedisDao redisDao;

    public UserServiceImpl(UserDao userDao, RedisDao redisDao) {
        this.userDao = userDao;
        this.redisDao = redisDao;
    }

    @Override
    public User findByUsername(String username) {
        //return JsonUtil.deserialize(redisDao.getValue(username), User.class);
        return userDao.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        String json = JsonUtil.serialize(user);
        redisDao.setKey(user.getUsername(), json);
        userDao.save(user);
    }
}
