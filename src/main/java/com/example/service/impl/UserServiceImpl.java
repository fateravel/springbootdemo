package com.example.service.impl;

import com.example.controller.UserController;
import com.example.dao.RedisDao;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author pengsong
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserController userController;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisDao redisDao;

    @Override
    public User findByUsername(String username) {
        List<User> byUsername = userMapper.findByUsername(username);
        logger.info("数据结果:{}", byUsername);
        if (CollectionUtils.isEmpty(byUsername)) {
            return null;
        }
        return byUsername.get(0);
    }

    @Override
    public void addUser(User user) {
        String json = JsonUtil.serialize(user);
        redisDao.setKey(user.getUsername(), json);
        //userDao.save(user);
    }
}
