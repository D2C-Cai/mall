package com.d2c.product.rabbitmq.receiver;


import com.d2c.product.business.model.Product;
import com.d2c.product.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect1(Product product) {
        System.out.println("【receiveDirect1监听到消息】" + product);
    }

    @RabbitListener(queues = RabbitConfig.DIRECT_QUEUE1)
    public void receiveDirect2(Product product) {
        System.out.println("【receiveDirect2监听到消息】" + product);
    }

}
