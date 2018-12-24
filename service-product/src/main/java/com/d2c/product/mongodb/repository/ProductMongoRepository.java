package com.d2c.product.mongodb.repository;


import com.d2c.product.mongodb.document.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductMongoRepository extends MongoRepository<ProductMongo, Long> {

    List<ProductMongo> findBySn(String sn);

}
