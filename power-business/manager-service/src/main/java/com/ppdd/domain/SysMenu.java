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
 * 菜单管理
 */
@Schema(description="菜单管理")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.INPUT)
    @Schema(description="")
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField(value = "parent_id")
    @Schema(description="父菜单ID，一级菜单为0")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField(value = "`name`")
    @Schema(description="菜单名称")
    private String name;

    /**
     * 菜单URL
     */
    @TableField(value = "url")
    @Schema(description="菜单URL")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @TableField(value = "perms")
    @Schema(description="授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @TableField(value = "`type`")
    @Schema(description="类型   0：目录   1：菜单   2：按钮")
    private Integer type;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @Schema(description="菜单图标")
    private String icon;

    /**
     * 排序
     */
    @TableField(value = "order_num")
    @Schema(description="排序")
    private Integer orderNum;
}