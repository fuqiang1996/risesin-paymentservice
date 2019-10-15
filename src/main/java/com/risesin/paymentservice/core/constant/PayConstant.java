package com.risesin.paymentservice.core.constant;

import java.io.File;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/12
 * @DESCRIPTION 支付接口中的一些常量
 * @since 1.0.0
 */
public class PayConstant {

    public final static byte PAY_STATUS_EXPIRED = -2; 	// 订单过期
    public final static byte PAY_STATUS_FAILED = -1; 	// 支付失败
    public final static byte PAY_STATUS_INIT = 0; 		// 初始态
    public final static byte PAY_STATUS_PAYING = 1; 	// 支付中
    public final static byte PAY_STATUS_SUCCESS = 2; 	// 支付成功
    public final static byte PAY_STATUS_COMPLETE = 3; 	// 业务完成

    /**
     * 异步通知 支付成功
     */
    public static final String AYSC_NOTIFY_PAY_SUCCESS = "success";
    /**
     * 下单支付失败
     */
    public static final String RETURN_ALIPAY_VALUE_FAIL = "fail";



}
