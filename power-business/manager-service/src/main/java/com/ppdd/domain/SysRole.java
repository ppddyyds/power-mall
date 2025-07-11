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
 * 角色
 */
@Schema(description="角色")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.sys_role")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.INPUT)
    @Schema(description="")
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @Schema(description="角色名称")
    private String roleName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @Schema(description="备注")
    private String remark;

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
}