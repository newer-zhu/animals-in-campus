package com.nothing.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nothing.user.mapper.RoleMapper;
import com.nothing.user.model.Role;
import com.nothing.user.service.IRoleService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuhodor
 * @since 2022-11-30
 */
@DubboService
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
