package com.d2c.product.business.service;


import com.d2c.product.business.model.Product;
import com.d2c.product.elasticsearch.document.ProductSearch;
import com.d2c.product.mongodb.document.ProductMongo;

import java.util.List;

public interface ProductService {

    Product findBySn(String sn);

    Product findCacheBySn(String sn);

    List<ProductMongo> findMongoBySn(String sn);

    List<ProductSearch> findSearchBySn(String sn);

}
