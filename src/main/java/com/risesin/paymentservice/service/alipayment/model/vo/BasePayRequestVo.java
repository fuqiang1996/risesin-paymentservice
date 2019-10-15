package com.risesin.paymentservice.service.alipayment.model.vo;

import com.risesin.paymentservice.core.groups.PayGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 基础请求支付信息
 * @since 1.0.0
 */
@Data
@ApiModel(value = "基础支付请求对象", description = "基础支付请求对象")
public class BasePayRequestVo {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "商户订单号",required = true)
    @NotNull(message = "商户订单号不能为空" , groups = PayGroup.class)
    private String out_trade_no; // 商户订单号,订单支付时传入的商户订单号,和支付宝交易号不能同时为空。

}
