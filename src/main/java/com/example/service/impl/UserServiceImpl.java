package com.example.service.impl;

import com.example.controller.UserController;
import com.example.dao.RedisDao;
import com.example.entity.User;
import com.example.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public User findByUsername(String username) {
        //return JsonUtil.deserialize(redisDao.getValue(username), User.class);
        return userMapper.findByUsername(username).get(0);
    }

    @Override
    public void addUser(User user) {
        String json = JsonUtil.serialize(user);
        redisDao.setKey(user.getUsername(), json);
        //userDao.save(user);
    }
}
