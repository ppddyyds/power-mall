package com.ppdd.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统用户
 */
@Schema(description="系统用户")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.sys_user")
public class LoginSysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.INPUT)
    @Schema(description="")
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @Schema(description="用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @Schema(description="密码")
    private String password;


    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "`status`")
    @Schema(description="状态  0：禁用   1：正常")
    private Integer status;

    /**
     * 用户所在的商城Id
     */
    @TableField(value = "shop_id")
    @Schema(description="用户所在的商城Id")
    private Long shopId;
}