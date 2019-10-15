package com.risesin.paymentservice.core.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.risesin.common.utils.spring.SpringUtil;
import com.risesin.paymentservice.core.properties.AliPaymentProperties;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/9
 * @DESCRIPTION 阿里支付工具类
 * @since 1.0.0
 */
public class AlipayClientUtil {
    // 获取配置类
    private static AliPaymentProperties aliPaymentProperties = SpringUtil.getBean("paymentProperties", AliPaymentProperties.class);

    private static final String FORMAT = "json";
    private static final String CHARSET = "utf-8";

    /**
     * 返回阿里支付客户端
     * @return
     */
    public static AlipayClient getAlipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPaymentProperties.getUrl(),
                aliPaymentProperties.getAppid(),
                aliPaymentProperties.getPrivateKey(),
                FORMAT,
                CHARSET,
                aliPaymentProperties.getAliPublicKey(),
                aliPaymentProperties.getSignType()
        );
        return alipayClient;
    }

}
