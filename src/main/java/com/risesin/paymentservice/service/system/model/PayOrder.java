package com.risesin.paymentservice.service.system.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/12
 * @DESCRIPTION 支付订单实体
 * @since 1.0.0
 */
@Accessors(chain = true) // 可将对象转换成链式设置值(流的形式)
@Data
@ApiModel(value = "订单对象", description = "支付订单")
public class PayOrder implements Serializable {
    /**
     * 支付订单号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "支付订单号" , required = true)
    private String payOrderId;

    /**
     * 商户ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "商户ID" , required = true)
    private String mchId;

    /**
     * 商户订单号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "商户订单号" , required = true)
    private String mchOrderNo;

    /**
     * 渠道ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "渠道ID" , required = true)
    private String channelId;

    /**
     * 支付金额,单位分
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "支付金额,单位分" , required = true)
    private Long amount;

    /**
     * 三位货币代码,人民币:cny
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "三位货币代码,人民币:cny" , required = true)
    private String currency;

    /**
     * 支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "支付状态,0-订单生成,1-支付中(目前未使用),2-支付成功,3-业务处理完成" , required = true)
    private Byte status;

    /**
     * 客户端IP
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "客户端IP" )
    private String clientIp;

    /**
     * 设备
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "设备" , required = true)
    private String device;

    /**
     * 商品标题
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "商品标题" , required = true)
    private String subject;

    /**
     * 商品描述信息
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "商品描述信息" )
    private String body;

    /**
     * 特定渠道发起时额外参数
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "特定渠道发起时额外参数" )
    private String extra;

    /**
     * 渠道商户ID
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "渠道商户ID" , required = true)
    private String channelMchId;

    /**
     * 渠道订单号
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "渠道订单号" )
    private String channelOrderNo;

    /**
     * 渠道支付错误码
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "渠道支付错误码" )
    private String errCode;

    /**
     * 渠道支付错误描述
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "渠道支付错误描述" )
    private String errMsg;

    /**
     * 扩展参数1
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "扩展参数1" )
    private String param1;

    /**
     * 扩展参数2
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "扩展参数2" )
    private String param2;

    /**
     * 通知地址
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "商户ID" )
    private String notifyUrl;

    /**
     * 通知次数
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "通知次数" )
    private Byte notifyCount;

    /**
     * 最后一次通知时间
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "最后一次通知时间" )
    private Long lastNotifyTime;

    /**
     * 订单失效时间
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "订单失效时间" )
    private Long expireTime;

    /**
     * 订单支付成功时间
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "订单支付成功时间" )
    private Long paySuccTime;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "创建时间" )
    private LocalDateTime createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    @ApiModelProperty(value = "更新时间" )
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}
