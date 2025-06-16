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
 * 系统日志
 */
@Schema(description="系统日志")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.sys_log")
public class SysLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "user_id")
    @Schema(description="用户名")
    private Long userId;

    /**
     * 用户操作
     */
    @TableField(value = "`operation`")
    @Schema(description="用户操作")
    private String operation;

    /**
     * 请求方法
     */
    @TableField(value = "`method`")
    @Schema(description="请求方法")
    private String method;

    /**
     * 请求参数
     */
    @TableField(value = "params")
    @Schema(description="请求参数")
    private String params;

    /**
     * 执行时长(毫秒)
     */
    @TableField(value = "`time`")
    @Schema(description="执行时长(毫秒)")
    private Long time;

    /**
     * IP地址
     */
    @TableField(value = "ip")
    @Schema(description="IP地址")
    private String ip;

    /**
     * 创建时间
     */
    @TableField(value = "create_date")
    @Schema(description="创建时间")
    private Date createDate;
}