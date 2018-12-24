package com.d2c.order.rabbitmq.receiver;


import com.d2c.order.business.model.Order;
import com.d2c.order.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE1)
    public void receiveTopic1(Order order) {
        System.out.println("【receiveTopic1监听到消息】" + order.toString());
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE2)
    public void receiveTopic2(Order order) {
        System.out.println("【receiveTopic2监听到消息】" + order.toString());
    }

}
