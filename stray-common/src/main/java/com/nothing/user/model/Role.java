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
 * @since 2022-11-30
 */
@Getter
@Setter
@TableName("s_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色
     */
    @TableField("role_cn_name")
    private String roleCnName;

    /**
     * 角色字符串
     */
    @TableField("role_name")
    private String roleName;

    @TableField("deleted")
    @TableLogic
    private Byte deleted;
}
