package com.d2c.product.rabbitmq.receiver;


import com.d2c.product.business.model.Product;
import com.d2c.product.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    public void receiveTopic1(Product product) {
        System.out.println("【receiveTopic1监听到消息】" + product.toString());
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    public void receiveTopic2(Product product) {
        System.out.println("【receiveTopic2监听到消息】" + product.toString());
    }

}
