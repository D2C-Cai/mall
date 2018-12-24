package com.d2c.product.rabbitmq.sender;


import com.d2c.product.business.model.Product;
import com.d2c.product.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Product product) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, "direct.d2cmall", product);
    }
}
