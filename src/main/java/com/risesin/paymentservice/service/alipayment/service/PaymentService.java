package com.risesin.paymentservice.service.alipayment.service;

import com.risesin.paymentservice.service.alipayment.model.vo.PayRequestVo;
import com.risesin.paymentservice.service.alipayment.model.dto.PayResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/8
 * @DESCRIPTION 支付服务接口
 * @since 1.0.0
 */
public interface PaymentService {

    /**
     * 支付
     * @return
     */
    PayResponse pay(PayRequestVo payRequestVo);

    /**
     * 异步通知
     * @param httpServletRequest
     * @return
     */
    String aliNotify(HttpServletRequest httpServletRequest);
}
