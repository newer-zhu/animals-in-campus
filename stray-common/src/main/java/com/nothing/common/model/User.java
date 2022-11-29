package com.nothing.common.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zhuhodor
 * @since 2022-11-26
 */
@Getter
@Setter
@TableName("s_user")
//@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("phone")
    private String phone;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("avatar")
    private String avatar;

    @TableField("is_valid")
    private Integer isValid;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
