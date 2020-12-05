package com.example.service.impl;

import com.example.controller.UserController;
import com.example.dao.RedisDao;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pengsong
 */
@Slf4j
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
        List<User> byUsername = userMapper.findByUsername(username);
        log.info("数据结果:{}", byUsername);
        if (byUsername.size() > 0) {
            return byUsername.get(0);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        String json = JsonUtil.serialize(user);
        redisDao.setKey(user.getUsername(), json);
        //userDao.save(user);
    }
}
