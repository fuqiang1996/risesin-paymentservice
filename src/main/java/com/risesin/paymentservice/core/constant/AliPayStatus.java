package com.risesin.paymentservice.core.constant;

import lombok.Getter;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/11
 * @DESCRIPTION ali网关返回码 状态
 * @since 1.0.0
 */
@Getter
public enum AliPayStatus {

    SUCCESS("支付接口调用成功","10000"),
    UNUSE("服务不可用","20000"),
    UNAUTH("授权权限不足","20001"),
    LACK_NECESSARILY_PARAM("缺少必选参数","40001"),
    ILLEGAL_PARAM("非法参数","40002"),
    FAIL("业务处理失败","40004"),
    NO_PERMISSON("权限不足","40006"),
    ASYNPAY("异步支付","1000");

    public String code(){
        return this.code;
    }
    public String title(){
        return this.title;
    }

    private AliPayStatus(String title, String code){
        this.title = title;
        this.code = code;
    }
    private String title;
    private String code;
}
