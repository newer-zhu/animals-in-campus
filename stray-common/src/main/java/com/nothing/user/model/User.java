package com.nothing.user.model;

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
    private Boolean isValid;

    @TableField("deleted")
    @TableLogic
    private Integer deleted;


}
