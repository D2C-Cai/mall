package com.d2c.product.business.service.impl;


import com.codingapi.tx.annotation.ITxTransaction;
import com.d2c.product.business.dao.ProductMapper;
import com.d2c.product.business.model.Product;
import com.d2c.product.business.service.ProductService;
import com.d2c.product.elasticsearch.document.ProductSearch;
import com.d2c.product.elasticsearch.repository.ProductSearchRepository;
import com.d2c.product.mongodb.document.ProductMongo;
import com.d2c.product.mongodb.repository.ProductMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService, ITxTransaction {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductMongoRepository productMongoRepository;
    @Autowired
    private ProductSearchRepository productSearchRepository;

    @Override
    public Product findBySn(String sn) {
        Product product = productMapper.findBySn(sn);
        redisTemplate.opsForValue().set("order_" + sn, product);
        productMongoRepository.save(new ProductMongo(product));
        productSearchRepository.save(new ProductSearch(product));
        return product;
    }

    @Override
    public Product findCacheBySn(String sn) {
        return (Product) redisTemplate.opsForValue().get("order_" + sn);
    }

    @Override
    public List<ProductMongo> findMongoBySn(String sn) {
        return productMongoRepository.findBySn(sn);
    }

    @Override
    public List<ProductSearch> findSearchBySn(String sn) {
        return productSearchRepository.findBySn(sn);
    }

    @Override
    @Transactional
    public int updatePriceById(Long id, BigDecimal price) {
        return productMapper.updatePriceById(id, price);
    }

}
