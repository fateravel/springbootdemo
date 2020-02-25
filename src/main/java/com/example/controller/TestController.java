package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("测试类")
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @ApiOperation("获取服务列表")
    @GetMapping("/service_list")
    public List<ServiceInstance> getServiceList() {
        List<ServiceInstance> instances = discoveryClient.getInstances("springboot-demo");
        return instances;
    }
}
