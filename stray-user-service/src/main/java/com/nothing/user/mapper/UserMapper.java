package com.nothing.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nothing.common.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhuhodor
 * @since 2022-11-26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
