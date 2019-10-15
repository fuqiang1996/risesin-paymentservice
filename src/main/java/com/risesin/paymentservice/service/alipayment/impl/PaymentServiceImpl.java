package com.risesin.paymentservice.service.alipayment.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.risesin.common.utils.fastJson.JsonUtils;
import com.risesin.common.utils.tools.MapUtil;
import com.risesin.common.vo.resultVo.RC;
import com.risesin.paymentservice.core.constant.AliTradeStatus;
import com.risesin.paymentservice.core.constant.PayConstant;
import com.risesin.paymentservice.core.constant.AliPayStatus;
import com.risesin.paymentservice.core.exception.PayException;
import com.risesin.paymentservice.core.properties.AliPaymentProperties;
import com.risesin.paymentservice.core.utils.AlipayClientUtil;
import com.risesin.paymentservice.service.alipayment.model.bo.AliPayResponseBO;
import com.risesin.paymentservice.service.alipayment.service.PaymentService;
import com.risesin.paymentservice.service.alipayment.model.vo.PayRequestVo;
import com.risesin.paymentservice.service.alipayment.model.dto.PayResponse;
import com.risesin.paymentservice.service.system.model.PayOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/8
 * @DESCRIPTION 支付服务
 * @since 1.0.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private AliPaymentProperties aliPaymentProperties;

    // 支付宝服务器主动通知商户服务器里指定的页面http/https路径。
    private static final String ALI_PAY_NOTIFY_URL = "http://localhost:9010/api/service/payment/notify";
    // 支付宝服务器返回路径
    private static final String ALI_PAY_RETURN_URL = "http://localhost:9010/api/service/payment/return"; //
    private static final String ALI_PAY_VERSION = "1.0"; // 调用的接口版本，固定为：1.0
    private static final String CHARSET = "utf-8"; // 调用的接口版本，固定为：1.0

    // 日志前缀
    private static final String LOG_PAY_PREFIX = "【支付宝下单并且支付】";
    private static final String LOG_PAY_NOTIFY_PREFIX = "【支付宝支付回调通知】";


    /**
     * 下单并支付
     * @param payRequestVo
     * @return
     */
    @Override
    public PayResponse pay(PayRequestVo payRequestVo) {

        log.info(LOG_PAY_PREFIX + " 参数 :" + JsonUtils.convertObjectToJSON(payRequestVo));

        if(Objects.isNull(payRequestVo)){
            throw new PayException(RC.C403);
        }

        AlipayClient alipayClient = AlipayClientUtil.getAlipayClient();
        AlipayTradePagePayRequest alipayTradePagePayRequest = getAlipayTradePagePayRequest(payRequestVo);
        try {
            // 执行支付请求
            AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayTradePagePayRequest);

            if(response.isSuccess()){
                AliPayResponseBO aliPayResponseBO = JsonUtils.convertJsonToBean(response.getBody(), AliPayResponseBO.class);

                // 验证签名
                boolean isSign = AlipaySignature.rsaCheck(aliPayResponseBO.getAlipay_trade_page_pay_response(), aliPayResponseBO.getSign(),
                        aliPaymentProperties.getAliPublicKey(), CHARSET, aliPaymentProperties.getSignType());

                if(!isSign){
                    log.error(LOG_PAY_PREFIX + " 签名错误");
                    throw new PayException("签名错误");
                }

                PayResponse payResponse = JsonUtils.convertJsonToBean(aliPayResponseBO.getAlipay_trade_page_pay_response(), PayResponse.class);
                // 判断ali网关是否调用成功
                if(AliPayStatus.SUCCESS.code().equals(payResponse.getCode()) && AliPayStatus.SUCCESS.title().equals(payResponse.getMsg())){
                    log.info(LOG_PAY_PREFIX + " 下单并支付订单创建成功");
                    return payResponse;
                }else{
                    log.error(LOG_PAY_PREFIX + "ali网关返回错误");
                    throw new PayException(Integer.valueOf(payResponse.getCode()),payResponse.getMsg());
                }

            } else {
                log.error(LOG_PAY_PREFIX + "支付失败!!!");
            }
        } catch (AlipayApiException e) {
            log.error(LOG_PAY_PREFIX + e.getErrMsg());
            throw new PayException(Integer.valueOf(e.getErrCode()),"支付失败,请检查网络: " + e.getErrMsg(),e);
        }
        return null;
    }

    /**
     * 获取支付request
     * @return
     */
    public AlipayTradePagePayRequest getAlipayTradePagePayRequest(PayRequestVo payRequestVo){
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setApiVersion(ALI_PAY_VERSION);
        request.setNotifyUrl(ALI_PAY_NOTIFY_URL);
        request.setReturnUrl(ALI_PAY_RETURN_URL);
        request.setBizContent(JsonUtils.convertObjectToJSON(payRequestVo));
        return request;
    }



    /**
     * 支付异步通知
     * @param request
     * @return
     */
    @Override
    public String aliNotify(HttpServletRequest request) {

        if(Objects.isNull(request.getParameterMap())){
            throw new PayException(RC.C403);
        }
        // 对请求参数Map的转换
        Map<String, String> paramMap = MapUtil.getParamMapInRequestMap(request.getParameterMap());

        log.info("{}通知请求数据:reqStr={}", LOG_PAY_NOTIFY_PREFIX, paramMap);
        if(paramMap.isEmpty()) {
            log.error("{}请求参数为空", LOG_PAY_NOTIFY_PREFIX);
            return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
        }
        // ===验证参数===
        // 支付上下文信息
        Map<String, Object> payContext = new HashMap();
        payContext.put("parameters",paramMap);

        // TODO 验证参数, 还未完善 优先级不高
        if(!vertifyAliParam(payContext)){
            log.error("{}请求参数验证失败", LOG_PAY_NOTIFY_PREFIX);
            return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
        }
        log.info("{}验证请求数据及签名通过", LOG_PAY_NOTIFY_PREFIX);

        // 交易状态
        String trade_status = paramMap.get("trade_status");
        // 定义支付订单
        PayOrder payOrder = null;
        // 支付状态成功或者完成 TODO 更新业务处理状态

        if (trade_status.equals(AliTradeStatus.TRADE_SUCCESS.status()) ||
                trade_status.equals(AliTradeStatus.TRADE_FINISHED.status())) {

            int updatePayOrderRows;
//            payOrder = (PayOrder)payContext.get("payOrder");
//            byte payStatus = payOrder.getStatus(); // 0：订单生成，1：支付中，-1：支付失败，2：支付成功，3：业务处理完成，-2：订单过期
//            if (payStatus != PayConstant.PAY_STATUS_SUCCESS && payStatus != PayConstant.PAY_STATUS_COMPLETE) {
//                updatePayOrderRows = payOrderService.updateStatus4Success(payOrder.getPayOrderId());
//                if (updatePayOrderRows != 1) {
//                    log.error("{}更新支付状态失败,将payOrderId={},更新payStatus={}失败", LOG_PAY_NOTIFY_PREFIX, payOrder.getPayOrderId(), PayConstant.PAY_STATUS_SUCCESS);
//                    log.info("{}响应给支付宝结果：{}", LOG_PAY_NOTIFY_PREFIX, PayConstant.RETURN_ALIPAY_VALUE_FAIL);
//                    return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
//                }
//                log.info("{}更新支付状态成功,将payOrderId={},更新payStatus={}成功", LOG_PAY_NOTIFY_PREFIX, payOrder.getPayOrderId(), PayConstant.PAY_STATUS_SUCCESS);
//                payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
//            }
//        }else{
//            // 其他状态
//            log.info("{}支付状态trade_status={},不做业务处理", LOG_PAY_NOTIFY_PREFIX, trade_status);
//            log.info("{}响应给支付宝结果：{}", LOG_PAY_NOTIFY_PREFIX, PayConstant.AYSC_NOTIFY_PAY_SUCCESS);
//            return PayConstant.AYSC_NOTIFY_PAY_SUCCESS;
        }

        // 通知业务系统 回调完成
        doNotify(payOrder);
        log.info("====== 完成接收支付宝支付回调通知 ======");
        return PayConstant.AYSC_NOTIFY_PAY_SUCCESS;
    }

    /**
     * 验证阿里参数
     * @return
     */
    private boolean vertifyAliParam(Map<String,Object> payContext){
        Map<String,String> params = (Map<String,String>)payContext.get("parameters");
        String out_trade_no = params.get("out_trade_no");		// 商户订单号
        String total_amount = params.get("total_amount"); 		// 支付金额
        if (StringUtils.isEmpty(out_trade_no)) {
            log.error("AliPay Notify parameter out_trade_no is empty. out_trade_no={}", out_trade_no);
            payContext.put("retMsg", "out_trade_no is empty");
            return false;
        }
        if (StringUtils.isEmpty(total_amount)) {
            log.error("AliPay Notify parameter total_amount is empty. total_fee={}", total_amount);
            payContext.put("retMsg", "total_amount is empty");
            return false;
        }
        String errorMessage;
        // 查询payOrder记录
        String payOrderId = out_trade_no;
//        PayOrder payOrder = payOrderService.selectPayOrder(payOrderId);
//        if (payOrder == null) {
//            log.error("Can't found payOrder form db. payOrderId={}, ", payOrderId);
//            payContext.put("retMsg", "Can't found payOrder");
//            return false;
//        }
//        // 查询payChannel记录
//        String mchId = payOrder.getMchId();
//        String channelId = payOrder.getChannelId();
//        PayChannel payChannel = payChannelService.selectPayChannel(channelId, mchId);
//        if(payChannel == null) {
//            log.error("Can't found payChannel form db. mchId={} channelId={}, ", payOrderId, mchId, channelId);
//            payContext.put("retMsg", "Can't found payChannel");
//            return false;
//        }
        boolean verify_result = false;
        try {
            verify_result = AlipaySignature.rsaCheckV1(params, aliPaymentProperties.getAliPublicKey(), CHARSET, "RSA2");
        } catch (AlipayApiException e) {
            log.error( "AlipaySignature.rsaCheckV1 error",e);
        }

        // 验证签名
        if (!verify_result) {
            errorMessage = "rsaCheckV1 failed.";
            log.error("AliPay Notify parameter {}", errorMessage);
            payContext.put("retMsg", errorMessage);
            return false;
        }

        // 核对金额
        long aliPayAmt = new BigDecimal(total_amount).movePointRight(2).longValue();
//        long dbPayAmt = payOrder.getAmount().longValue();
//        if (dbPayAmt != aliPayAmt) {
//            log.error("db payOrder record payPrice not equals total_amount. total_amount={},payOrderId={}", total_amount, payOrderId);
//            payContext.put("retMsg", "");
//            return false;
//        }
//        payContext.put("payOrder", payOrder);
        return true;
    }

    /**
     * 处理支付结果后台服务器通知 TODO 未完善 ,可以使用MQ
     */
    public void doNotify(PayOrder payOrder) {
        log.info(">>>>>> 阿里PAY开始回调通知业务系统 <<<<<<");
        // 发起后台通知业务系统
//        try {
//            mq4PayNotify.send(object.toJSONString());
//        } catch (Exception e) {
//            _log.error("payOrderId={},sendMessage error.", payOrder != null ? payOrder.getPayOrderId() : "", e);
//        }
        log.info(">>>>>> PAY回调通知业务系统完成 <<<<<<");
    }



}
