package com.nothing.gateway.controller;

import com.nothing.user.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Hodor_Zhu
 * @description
 * @date: 2022/11/29 18:52
 */
@RestController
public class TestController {
    @DubboReference
    private IUserService userService;

    @RequestMapping("/test")
    public String test(){
        return userService.getAllUsers().toString();
    }
}
