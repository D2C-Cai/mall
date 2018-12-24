package com.d2c.order.business.service.impl;


import com.d2c.order.business.dao.OrderMapper;
import com.d2c.order.business.model.Order;
import com.d2c.order.business.service.OrderService;
import com.d2c.order.elasticsearch.document.OrderSearch;
import com.d2c.order.elasticsearch.repository.OrderSearchRepository;
import com.d2c.order.mongodb.document.OrderMongo;
import com.d2c.order.mongodb.repository.OrderMongoRepository;
import com.d2c.order.rabbitmq.sender.DirectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderMongoRepository orderMongoRepository;
    @Autowired
    private OrderSearchRepository orderSearchRepository;
    @Autowired
    private DirectSender directSender;

    @Override
    public Order findBySn(String sn) {
        Order order = orderMapper.findBySn(sn);
        redisTemplate.opsForValue().set("order_" + sn, order);
        orderMongoRepository.save(new OrderMongo(order));
        orderSearchRepository.save(new OrderSearch(order));
        directSender.send(order);
        return order;
    }

    @Override
    public Order findCacheBySn(String sn) {
        return (Order) redisTemplate.opsForValue().get("order_" + sn);
    }

    @Override
    public List<OrderMongo> findMongoBySn(String sn) {
        return orderMongoRepository.findBySn(sn);
    }

    @Override
    public List<OrderSearch> findSearchBySn(String sn) {
        return orderSearchRepository.findBySn(sn);
    }

}
