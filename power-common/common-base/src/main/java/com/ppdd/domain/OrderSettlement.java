package com.ppdd.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mall.order_settlement")
public class OrderSettlement implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 支付结算单据ID
     */
    @TableId(value = "settlement_id", type = IdType.INPUT)
    @Schema(description="支付结算单据ID")
    private Long settlementId;

    /**
     * 外部订单流水号
     */
    @TableField(value = "biz_pay_no")
    @Schema(description="外部订单流水号")
    private String bizPayNo;

    /**
     * order表中的订单号
     */
    @TableField(value = "order_number")
    @Schema(description="order表中的订单号")
    private String orderNumber;

    /**
     * 支付方式 1 支付宝 2 微信
     */
    @TableField(value = "pay_type")
    @Schema(description="支付方式 1 支付宝 2 微信")
    private Integer payType;

    /**
     * 支付金额
     */
    @TableField(value = "pay_amount")
    @Schema(description="支付金额")
    private BigDecimal payAmount;

    /**
     * 是否清算 0:否 1:是
     */
    @TableField(value = "is_clearing")
    @Schema(description="是否清算 0:否 1:是")
    private Integer isClearing;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description="创建时间")
    private Date createTime;

    /**
     * 清算时间
     */
    @TableField(value = "clearing_time")
    @Schema(description="清算时间")
    private Date clearingTime;

    /**
     * 版本号
     */
    @TableField(value = "version")
    @Schema(description="版本号")
    private Integer version;

    /**
     * 支付状态
     */
    @TableField(value = "pay_status")
    @Schema(description="支付状态")
    private Integer payStatus;
}