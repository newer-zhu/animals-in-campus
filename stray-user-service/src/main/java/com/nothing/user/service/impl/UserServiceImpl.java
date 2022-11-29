package com.nothing.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nothing.common.model.User;
import com.nothing.user.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nothing.user.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

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

    public List<User> getAllUsers(){
        return userMapper.selectList(new QueryWrapper<User>());
    }

}
