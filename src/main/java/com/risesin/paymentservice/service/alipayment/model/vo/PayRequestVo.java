package com.risesin.paymentservice.service.alipayment.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 支付请求信息 BizContent
 * @since 1.0.0
 */
@Data
@ApiModel(value = "支付请求对象", description = "支付请求对象")
public class PayRequestVo extends BasePayRequestVo {
    @ApiModelProperty(value = "订单标题" , required = true)
    @NotEmpty(message = "订单标题不能为空" )
    private String subject = "";  // 订单标题

    @ApiModelProperty(value = "订单总金额，单位为元，精确到小数点后两位", required = true)
    @NotEmpty(message = "订单总金额不能为空")
    private String total_amount = ""; // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。

    @ApiModelProperty(value = "销售产品码，与支付宝签约的产品码名称" , required = true)
    @NotEmpty(message = "销售产品码不能为空")
    private String product_code = ""; // 销售产品码，与支付宝签约的产品码名称。注：目前仅支持FAST_INSTANT_TRADE_PAY

    @ApiModelProperty("该笔订单允许的最晚付款时间，逾期将关闭交易")
    private String timeout_express = ""; // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m

    @ApiModelProperty("订单描述")
    private String body = ""; // 订单描述

    @ApiModelProperty("商户门店号")
    private String store_id = ""; // 商户门店号
}
