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

@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.area")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "area_id", type = IdType.INPUT)
    @Schema(description="")
    private Long areaId;

    @TableField(value = "area_name")
    @Schema(description="")
    private String areaName;

    @TableField(value = "parent_id")
    @Schema(description="")
    private Long parentId;

    @TableField(value = "`level`")
    @Schema(description="")
    private Integer level;
}