package com.risesin.paymentservice.service.alipayment.model.bo;

import lombok.Data;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 阿里响应信息
 * @since 1.0.0
 */
@Data
public class AliPayResponseBO {
    private String alipay_trade_query_response; // 查询交易情况返回值
    private String alipay_trade_page_pay_response;// 支付情况返回值
    private String sign; // 签名
}
