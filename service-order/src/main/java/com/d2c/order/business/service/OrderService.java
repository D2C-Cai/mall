package com.d2c.order.business.service;

import com.d2c.order.business.model.Order;
import com.d2c.order.elasticsearch.document.OrderSearch;
import com.d2c.order.mongodb.document.OrderMongo;

import java.util.List;

public interface OrderService {

    Order findBySn(String sn);

    Order findCacheBySn(String sn);

    List<OrderMongo> findMongoBySn(String sn);

    List<OrderSearch> findSearchBySn(String sn);

    int doSomeThing(String sn, Long productId, Long memberId);

}
