package com.d2c.product.rabbitmq.sender;


import com.d2c.product.business.model.Product;
import com.d2c.product.config.rabbitmq.RabbitConfig;
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
    public void send(Product product) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "product.message", product);
        this.rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, "product.product", product);
    }

}
