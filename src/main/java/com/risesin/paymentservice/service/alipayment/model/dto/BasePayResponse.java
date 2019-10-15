package com.risesin.paymentservice.service.alipayment.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 基础返回信息
 * @since 1.0.0
 */
@Data
@ApiModel(value = "基础支付响应对象", description = "基础支付响应对象")
public class BasePayResponse {
    // 公共响应
    @ApiModelProperty(value = "网关返回码" )
    private String code; // 网关返回码
    @ApiModelProperty(value = "网关返回码描述" )
    private String msg; // 网关返回码描述
    @ApiModelProperty(value = "签名" )
    private String sign; // 签名
    @ApiModelProperty(value = "业务返回码" )
    private String sub_code; // 业务返回码
    @ApiModelProperty(value = "业务返回码描述" )
    private String sub_msg; // 业务返回码描述
    @ApiModelProperty(value = "支付宝交易号" )
    private String trade_no; // 支付宝交易号
    @ApiModelProperty(value = "商户订单号" )
    private String out_trade_no; // 商户订单号
    @ApiModelProperty(value = "交易金额" )
    private String total_amount; // 交易金额
}
