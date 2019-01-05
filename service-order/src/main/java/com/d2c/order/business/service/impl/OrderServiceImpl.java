package com.d2c.order.business.service.impl;


import com.codingapi.tx.annotation.TxTransaction;
import com.d2c.order.business.client.MemberClient;
import com.d2c.order.business.client.ProductClient;
import com.d2c.order.business.dao.OrderMapper;
import com.d2c.order.business.model.Order;
import com.d2c.order.business.service.OrderService;
import com.d2c.order.elasticsearch.document.OrderSearch;
import com.d2c.order.elasticsearch.repository.OrderSearchRepository;
import com.d2c.order.mongodb.document.OrderMongo;
import com.d2c.order.mongodb.repository.OrderMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MemberClient memberClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderMongoRepository orderMongoRepository;
    @Autowired
    private OrderSearchRepository orderSearchRepository;

    @Override
    public Order findBySn(String sn) {
        Order order = orderMapper.findBySn(sn);
        redisTemplate.opsForValue().set("order_" + sn, order);
        orderMongoRepository.save(new OrderMongo(order));
        orderSearchRepository.save(new OrderSearch(order));
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

    @Override
    @TxTransaction(isStart = true)
    @Transactional
    public int doSomeThing(String sn, Long productId, Long memberId) {

        int rs1 = orderMapper.updateAmountBySn(sn, new BigDecimal((int) (Math.random() * 100 + 1)));

        int rs2 = memberClient.updateNameById(memberId, String.valueOf((int) (Math.random() * 100 + 1)));

        int rs3 = productClient.updatePriceById(productId, new BigDecimal((int) (Math.random() * 100 + 1)));

        //return rs1 + rs2 + rs3;

        throw new RuntimeException("doSomeThing更新失败");
    }

}
