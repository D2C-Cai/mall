package com.d2c.order.rabbitmq.receiver;


import com.d2c.order.business.model.Order;
import com.d2c.order.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE1)
    public void receiveFanout1(Order order) {
        System.out.println("【receiveFanout1监听到消息】" + order);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE2)
    public void receiveFanout2(Order order) {
        System.out.println("【receiveFanout2监听到消息】" + order);
    }

}
