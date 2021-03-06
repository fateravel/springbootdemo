package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/user")
@RestController
@Api("用户表")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /*public UserController(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/{username}")
    @ApiOperation("获取用户名")
    public User getUser(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        logger.info("user info :" + user);
        return user;
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public void addUser(@RequestBody User user) {
        logger.info("add user info: " + JsonUtil.serialize(user));
        userService.addUser(user);
    }

    @GetMapping("/oom")
    @ApiOperation("oom测试")
    public void oom() {
        List<Byte[]> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                list.add(new Byte[1024 * 1024]);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("次数:" + i);
        }
    }
}
