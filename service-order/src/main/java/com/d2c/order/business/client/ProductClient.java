package com.d2c.order.business.client;

import com.d2c.order.business.client.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "service-product", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/api/product/update/{id}")
    int updatePriceById(@PathVariable(name = "id") Long id, @RequestParam(value = "price") BigDecimal price);

}
