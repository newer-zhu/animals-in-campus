package com.nothing.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nothing.common.exceptions.ApiException;
import com.nothing.user.model.User;
import com.nothing.user.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nothing.user.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuhodor
 * @since 2022-11-26
 */
@DubboService
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers(){
        return userMapper.selectList(new QueryWrapper<User>());
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public User creatUser(User user) {
        if (userMapper.selectCount(new QueryWrapper<User>().eq("phone", user.getPhone())) > 0){
            throw new ApiException("手机号已存在！");
        }else if (userMapper.selectCount(new QueryWrapper<User>().eq("username", user.getUsername())) > 0){
            throw new ApiException("用户名已存在！");
        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        return user;
    }

}
