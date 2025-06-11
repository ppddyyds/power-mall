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
 * 商品收藏表
 */
@Schema(description="商品收藏表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.prod_favorite")
public class ProdFavorite implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "favorite_id", type = IdType.INPUT)
    @Schema(description="主键")
    private Long favoriteId;

    /**
     * 商品ID
     */
    @TableField(value = "prod_id")
    @Schema(description="商品ID")
    private Long prodId;

    /**
     * 收藏时间
     */
    @TableField(value = "rec_time")
    @Schema(description="收藏时间")
    private Date recTime;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @Schema(description="用户ID")
    private String userId;
}