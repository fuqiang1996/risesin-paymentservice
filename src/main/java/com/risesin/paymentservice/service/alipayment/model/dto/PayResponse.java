package com.risesin.paymentservice.service.alipayment.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 支付响应参数
 * @since 1.0.0
 */
@Data
@ApiModel(value = "支付响应对象", description = "支付响应对象")
public class PayResponse extends BasePayResponse{
    @ApiModelProperty(value = "收款支付宝账号对应的支付宝唯一用户号。 以2088开头的纯16位数字\t2088111111116894" )
    private String seller_id; // 收款支付宝账号对应的支付宝唯一用户号。 以2088开头的纯16位数字	2088111111116894
    @ApiModelProperty(value = "商户原始订单号，最大长度限制32位" )
    private String merchant_order_no; // 商户原始订单号，最大长度限制32位

}