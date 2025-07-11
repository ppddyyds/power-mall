package com.ppdd.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统用户
 */
@Schema(description="系统用户")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.sys_user")
public class SysUser implements Serializable {
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
     * 邮箱
     */
    @TableField(value = "email")
    @Schema(description="邮箱")
    private String email;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @Schema(description="手机号")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "`status`")
    @Schema(description="状态  0：禁用   1：正常")
    private Byte status;

    /**
     * 创建者ID
     */
    @TableField(value = "create_user_id")
    @Schema(description="创建者ID")
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description="创建时间")
    private Date createTime;

    /**
     * 用户所在的商城Id
     */
    @TableField(value = "shop_id")
    @Schema(description="用户所在的商城Id")
    private Long shopId;
}