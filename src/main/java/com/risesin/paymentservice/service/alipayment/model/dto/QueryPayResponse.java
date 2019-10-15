package com.risesin.paymentservice.service.alipayment.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 查询支付信息返回响应
 * @since 1.0.0
 */
@Data
@ApiModel(value = "查询响应对象", description = "查询响应对象 ")
public class QueryPayResponse extends BasePayResponse {

    @ApiModelProperty(value = "业务返回码描述" )
    private String trade_status; // 业务返回码描述

    @ApiModelProperty(value = "订单支付币种" )
    private String pay_currency; // 订单支付币种

    @ApiModelProperty(value = "买家实付金额，单位为元，两位小数。该金额代表该笔交易买家实际支付的金额，不包含商户折扣等金额" )
    private String buyer_pay_amount; // 买家实付金额，单位为元，两位小数。该金额代表该笔交易买家实际支付的金额，不包含商户折扣等金额

    @ApiModelProperty(value = " 本次交易打款给卖家的时间" )
    private Date send_pay_date; // 本次交易打款给卖家的时间

    @ApiModelProperty(value = "实收金额，单位为元，两位小数。该金额为本笔交易，商户账户能够实际收到的金额" )
    private String receipt_amount; // 实收金额，单位为元，两位小数。该金额为本笔交易，商户账户能够实际收到的金额

    @ApiModelProperty(value = " 买家在支付宝的用户id" )
    private String buyer_user_id; // 买家在支付宝的用户id

    @ApiModelProperty(value = "买家名称；买家为个人用户时为买家姓名，买家为企业用户时为企业名称； 默认不返回该信息，需与支付宝约定后配置返回；" )
    private String buyer_user_name; // 买家名称；买家为个人用户时为买家姓名，买家为企业用户时为企业名称； 默认不返回该信息，需与支付宝约定后配置返回；
}
