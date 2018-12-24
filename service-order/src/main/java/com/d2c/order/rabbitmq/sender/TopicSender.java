package com.d2c.order.rabbitmq.sender;


import com.d2c.order.business.model.Order;
import com.d2c.order.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    // 第一个参数：TopicExchange名字
    // 第二个参数：Route-Key
    // 第三个参数：要发送的内容
    public void send(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "order.message", order);
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "order.order", order);
    }

}
