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
 * 主页轮播图
 */
@Schema(description="主页轮播图")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.index_img")
public class IndexImg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "img_id", type = IdType.INPUT)
    @Schema(description="主键")
    private Long imgId;

    /**
     * 店铺ID
     */
    @TableField(value = "shop_id")
    @Schema(description="店铺ID")
    private Long shopId;

    /**
     * 图片
     */
    @TableField(value = "img_url")
    @Schema(description="图片")
    private String imgUrl;

    /**
     * 说明文字,描述
     */
    @TableField(value = "des")
    @Schema(description="说明文字,描述")
    private String des;

    /**
     * 标题
     */
    @TableField(value = "title")
    @Schema(description="标题")
    private String title;

    /**
     * 链接
     */
    @TableField(value = "link")
    @Schema(description="链接")
    private String link;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    @Schema(description="状态")
    private Integer status;

    /**
     * 顺序
     */
    @TableField(value = "seq")
    @Schema(description="顺序")
    private Integer seq;

    /**
     * 上传时间
     */
    @TableField(value = "create_time")
    @Schema(description="上传时间")
    private Date createTime;

    /**
     * 关联
     */
    @TableField(value = "prod_id")
    @Schema(description="关联")
    private Long prodId;

    /**
     * 关联商品类型，0已关联商品,-1未关联商品
     */
    @TableField(value = "`type`")
    @Schema(description="关联商品类型，0已关联商品,-1未关联商品")
    private Integer type;
}