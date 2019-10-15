package com.risesin.paymentservice.service.alipayment.controller;

import com.risesin.common.vo.resultVo.R;
import com.risesin.paymentservice.core.constant.PayConstant;
import com.risesin.paymentservice.service.alipayment.model.dto.QueryPayResponse;
import com.risesin.paymentservice.service.alipayment.model.vo.QueryPayRequestVo;
import com.risesin.paymentservice.service.alipayment.service.PaymentService;
import com.risesin.paymentservice.service.alipayment.model.vo.PayRequestVo;
import com.risesin.paymentservice.service.alipayment.model.dto.PayResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/8
 * @DESCRIPTION 支付接口
 * @since 1.0.0
 */
@Api(tags = "阿里支付")
@RestController
@RequestMapping("api/service/payment/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 下单并支付
     * @param payRequestVo
     * @return
     */
    @PostMapping("pay")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "支付接口", notes = "支付payRequestVo")
    public R<PayResponse> pay(@ApiParam("支付请求") @Validated PayRequestVo payRequestVo){
        PayResponse pay = paymentService.pay(payRequestVo);
        return new R<>(pay);
    }

    /**
     * 异步通知
     * @param request
     */
    @PostMapping(value = "notify")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "异步支付通知", notes = "接受request")
    @ApiImplicitParam()
    public String aliNotify(@ApiParam("请求")HttpServletRequest request){
        paymentService.aliNotify(request);

        return PayConstant.AYSC_NOTIFY_PAY_SUCCESS;
    }

    /**
     * 查询支付结果
     * @param queryPayRequestVo
     */
    @PostMapping("query")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "查询支付结果", notes = "异步支付通知")
    public R<QueryPayResponse> query(@ApiParam("查询vo") @Validated QueryPayRequestVo queryPayRequestVo){

        return new R<>();
    }
    /**
     * 退款
     * @param request
     */
    @PostMapping("refund")
    public void refund(HttpServletRequest request){

    }

    /**
     * 退款查询
     * @param request
     */
    @PostMapping("refundQuery")
    public void refundQuery(HttpServletRequest request){

    }
    /**
     * 关闭交易
     * @param request
     */
    @PostMapping ("close")
    public void close(HttpServletRequest request){

    }
}
