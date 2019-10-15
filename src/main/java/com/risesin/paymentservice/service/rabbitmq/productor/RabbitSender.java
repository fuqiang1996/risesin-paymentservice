package com.risesin.paymentservice.service.rabbitmq.productor;

import com.risesin.common.utils.fastJson.JsonUtils;
import com.risesin.paymentservice.service.rabbitmq.config.QueueName;
import com.risesin.paymentservice.service.system.model.PayOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @AUTHOR Baby
 * @CREATE 2019/9/30
 * @DESCRIPTION 消息生产者
 * @since 1.0.0
 */
@Component
public class RabbitSender {
    private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 用于单生产者-》单消费者测试
     */
    public void send(PayOrder payOrder) {
        log.info("生产消息",payOrder);
        rabbitTemplate.convertAndSend(QueueName.ALI_PAY_NATICE_QUEUE, JsonUtils.convertObjectToJSON(payOrder));
    }

}
