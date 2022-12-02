package com.nothing.gateway.custom;

import com.nothing.user.model.User;
import com.nothing.user.service.IRoleService;
import com.nothing.user.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: Hodor_Zhu
 * @description
 * @date: 2022/12/1 0:07
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @DubboReference
    private IUserService userService;

    @DubboReference
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user == null) throw new UsernameNotFoundException("不存在此用户！");
        MyUserDetails userDetails = new MyUserDetails();
        BeanUtils.copyProperties(user, userDetails);
        //查找用户具有的角色
//            roleService.getObj()
        return userDetails;
    }
}
