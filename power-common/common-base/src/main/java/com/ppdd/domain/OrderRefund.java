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
@TableName(value = "mall.order_refund")
public class OrderRefund implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    @TableId(value = "refund_id", type = IdType.INPUT)
    @Schema(description="记录ID")
    private Long refundId;

    /**
     * 店铺ID
     */
    @TableField(value = "shop_id")
    @Schema(description="店铺ID")
    private Long shopId;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    @Schema(description="订单ID")
    private Long orderId;

    /**
     * 订单流水号
     */
    @TableField(value = "order_number")
    @Schema(description="订单流水号")
    private String orderNumber;

    /**
     * 订单总金额
     */
    @TableField(value = "order_amount")
    @Schema(description="订单总金额")
    private Double orderAmount;

    /**
     * 订单项ID 全部退款是0
     */
    @TableField(value = "order_item_id")
    @Schema(description="订单项ID 全部退款是0")
    private Long orderItemId;

    /**
     * 退款编号
     */
    @TableField(value = "refund_sn")
    @Schema(description="退款编号")
    private String refundSn;

    /**
     * 订单支付流水号
     */
    @TableField(value = "flow_trade_no")
    @Schema(description="订单支付流水号")
    private String flowTradeNo;

    /**
     * 第三方退款单号(微信退款单号)
     */
    @TableField(value = "out_refund_no")
    @Schema(description="第三方退款单号(微信退款单号)")
    private String outRefundNo;

    /**
     * 订单支付方式 1 微信支付 2 支付宝
     */
    @TableField(value = "pay_type")
    @Schema(description="订单支付方式 1 微信支付 2 支付宝")
    private Integer payType;

    /**
     * 订单支付名称
     */
    @TableField(value = "pay_type_name")
    @Schema(description="订单支付名称")
    private String payTypeName;

    /**
     * 买家ID
     */
    @TableField(value = "user_id")
    @Schema(description="买家ID")
    private String userId;

    /**
     * 退货数量
     */
    @TableField(value = "goods_num")
    @Schema(description="退货数量")
    private Integer goodsNum;

    /**
     * 退款金额
     */
    @TableField(value = "refund_amount")
    @Schema(description="退款金额")
    private BigDecimal refundAmount;

    /**
     * 申请类型:1,仅退款,2退款退货
     */
    @TableField(value = "apply_type")
    @Schema(description="申请类型:1,仅退款,2退款退货")
    private Integer applyType;

    /**
     * 处理状态:1为待审核,2为同意,3为不同意
     */
    @TableField(value = "refund_sts")
    @Schema(description="处理状态:1为待审核,2为同意,3为不同意")
    private Integer refundSts;

    /**
     * 处理退款状态: 0:退款处理中 1:退款成功 -1:退款失败
     */
    @TableField(value = "return_money_sts")
    @Schema(description="处理退款状态: 0:退款处理中 1:退款成功 -1:退款失败")
    private Integer returnMoneySts;

    /**
     * 申请时间
     */
    @TableField(value = "apply_time")
    @Schema(description="申请时间")
    private Date applyTime;

    /**
     * 卖家处理时间
     */
    @TableField(value = "handel_time")
    @Schema(description="卖家处理时间")
    private Date handelTime;

    /**
     * 退款时间
     */
    @TableField(value = "refund_time")
    @Schema(description="退款时间")
    private Date refundTime;

    /**
     * 文件凭证json
     */
    @TableField(value = "photo_files")
    @Schema(description="文件凭证json")
    private String photoFiles;

    /**
     * 申请原因
     */
    @TableField(value = "buyer_msg")
    @Schema(description="申请原因")
    private String buyerMsg;

    /**
     * 卖家备注
     */
    @TableField(value = "seller_msg")
    @Schema(description="卖家备注")
    private String sellerMsg;

    /**
     * 物流公司名称
     */
    @TableField(value = "express_name")
    @Schema(description="物流公司名称")
    private String expressName;

    /**
     * 物流单号
     */
    @TableField(value = "express_no")
    @Schema(description="物流单号")
    private String expressNo;

    /**
     * 发货时间
     */
    @TableField(value = "ship_time")
    @Schema(description="发货时间")
    private Date shipTime;

    /**
     * 收货时间
     */
    @TableField(value = "receive_time")
    @Schema(description="收货时间")
    private Date receiveTime;

    /**
     * 收货备注
     */
    @TableField(value = "receive_message")
    @Schema(description="收货备注")
    private String receiveMessage;
}