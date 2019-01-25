package com.d2c.product.business.controller;

import com.d2c.product.business.model.Product;
import com.d2c.product.business.service.ProductService;
import com.d2c.product.elasticsearch.document.ProductSearch;
import com.d2c.product.mongodb.document.ProductMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProductRestCtrl {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/api/product", method = RequestMethod.GET)
    public Product findBySn(@RequestParam(value = "sn", required = true) String sn) {
        return productService.findBySn(sn);
    }

    @RequestMapping(value = "/api/product/cache", method = RequestMethod.GET)
    public Product findCacheBySn(@RequestParam(value = "sn", required = true) String sn) {
        return productService.findCacheBySn(sn);
    }

    @RequestMapping(value = "/api/product/mongo", method = RequestMethod.GET)
    public List<ProductMongo> findMongoBySn(@RequestParam(value = "sn", required = true) String sn) {
        return productService.findMongoBySn(sn);
    }

    @RequestMapping(value = "/api/product/search", method = RequestMethod.GET)
    public List<ProductSearch> findSearchBySn(@RequestParam(value = "sn", required = true) String sn) {
        return productService.findSearchBySn(sn);
    }

    @RequestMapping(value = "/api/product/update/{id}", method = RequestMethod.GET)
    public int updatePriceById(@PathVariable(name = "id") Long id, @RequestParam(value = "price", required = true) BigDecimal price) {
        return productService.updatePriceById(id, price);
    }

}
