package com.d2c.member.rabbitmq.receiver;


import com.d2c.member.business.model.User;
import com.d2c.member.config.rabbitmq.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    // queues是指要监听的队列的名字
    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE1)
    public void receiveFanout1(User user) {
        System.out.println("【receiveFanout1监听到消息】" + user);
    }

    @RabbitListener(queues = RabbitConfig.FANOUT_QUEUE2)
    public void receiveFanout2(User user) {
        System.out.println("【receiveFanout2监听到消息】" + user);
    }

}
