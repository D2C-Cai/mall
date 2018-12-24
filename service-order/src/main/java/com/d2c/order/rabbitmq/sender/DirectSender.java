package com.d2c.order.rabbitmq.sender;


import com.d2c.order.business.model.Order;
import com.d2c.order.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "direct.d2cmall", order);
    }
}
