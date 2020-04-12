package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api("测试类")
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserMapper userMapper;

    @ApiOperation("获取服务列表")
    @GetMapping("/service_list")
    public List<ServiceInstance> getServiceList() {
        List<ServiceInstance> instances = discoveryClient.getInstances("springboot-demo");
        log.debug("service list------------");
        return instances;
    }

    @GetMapping("/starter")
    public Object starter(@RequestParam("id") String id) {
        User user = userMapper.findById(Long.parseLong(id));
        return user;
    }
}
