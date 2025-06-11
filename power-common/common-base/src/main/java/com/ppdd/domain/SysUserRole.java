package com.ppdd.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户与角色对应关系
 */
@Schema(description="用户与角色对应关系")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    @Schema(description="")
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @Schema(description="用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @Schema(description="角色ID")
    private Long roleId;
}