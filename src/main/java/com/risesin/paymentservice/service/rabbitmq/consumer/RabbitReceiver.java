package com.risesin.paymentservice.service.rabbitmq.consumer;

import com.risesin.common.utils.fastJson.JsonUtils;
import com.risesin.paymentservice.service.rabbitmq.config.QueueName;
import com.risesin.paymentservice.service.system.model.PayOrder;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/30
 * @DESCRIPTION 消费者
 * @since 1.0.0
 */
@RabbitListener(queues = QueueName.ALI_PAY_NATICE_QUEUE)
@Component
public class RabbitReceiver {

    @RabbitHandler
    public void process(String payOrder) {
        System.out.println(QueueName.ALI_PAY_NATICE_QUEUE+" Receiver  : " + JsonUtils.convertJsonToBean(payOrder,PayOrder.class));
    }
}
