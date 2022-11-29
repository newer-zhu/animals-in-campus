package com.nothing.user.controller;


import com.nothing.common.domain.Result;
import com.nothing.common.model.User;
import com.nothing.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  用户信息控制器
 * </p>
 *
 * @author zhuhodor
 * @since 2022-11-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/create")
    public Result<User> createUser(@RequestBody User user){
        return Result.success(new User());
    }
}
