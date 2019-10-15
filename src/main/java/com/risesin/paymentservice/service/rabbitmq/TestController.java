package com.risesin.paymentservice.service.rabbitmq;

import com.risesin.common.vo.resultVo.R;
import com.risesin.paymentservice.service.rabbitmq.productor.RabbitSender;
import com.risesin.paymentservice.service.system.model.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/30
 * @DESCRIPTION 测试接口
 * @since 1.0.0
 */
@RestController
public class TestController {

    @Autowired
    private RabbitSender rabbitSender;

    @Value("${risesin-properties.alipayment.appid}")
    private String appid;

    @RequestMapping("/api/msg/rabbit/send")
    public void send(){
        System.out.println(appid );
        for(int i = 0; i < 10000 ; i++){
            PayOrder payOrder = new PayOrder();
            payOrder.setBody("aaa").setCreateTime(LocalDateTime.now());
            rabbitSender.send(payOrder);
        }
    }
}
