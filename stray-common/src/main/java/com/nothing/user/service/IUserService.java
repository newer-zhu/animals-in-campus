package com.nothing.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nothing.user.model.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhuhodor
 * @since 2022-11-26
 */
public interface IUserService extends IService<User> {
    List<User> getAllUsers();

    User getUserByUserName(String username);

    User creatUser(User user);
}
