package com.risesin.paymentservice.core.properties;

import com.risesin.common.utils.date.LocalDateTimeUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * @AUTHOR Baby
 * @CREATE 2019/10/8
 * @DESCRIPTION 配置信息
 * @since 1.0.0
 */
@Component
@Data
@RefreshScope
@ConfigurationProperties("risesin-properties.alipayment")
public class AliPaymentProperties {
    private String publicKey;
    private String privateKey;
    private String aliPublicKey; // ali 公钥
    private String appid; // Appid
    private String sign; // 商户签名
    private String signType; // 类型
    private String url; // 请求URL
    private String timestamp; // 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"

    public String getTimestamp() {
        String currentDateTimeStr = LocalDateTimeUtils.getCurrentDateTimeStr();
        return currentDateTimeStr;
    }

    public void setTimestamp(String timestamp) {
        String currentDateTimeStr = LocalDateTimeUtils.getCurrentDateTimeStr();
        this.timestamp = currentDateTimeStr;
    }
}
