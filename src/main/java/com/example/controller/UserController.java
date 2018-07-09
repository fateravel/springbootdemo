package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@Api(description = "用户表")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @ApiOperation("获取用户名")
    public User getUser(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }
}
