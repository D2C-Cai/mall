package com.d2c.order.mongodb.repository;


import com.d2c.order.mongodb.document.OrderMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderMongoRepository extends MongoRepository<OrderMongo, Long> {

    List<OrderMongo> findBySn(String sn);

}
