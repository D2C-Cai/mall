package com.d2c.portal.client;

import com.d2c.portal.client.fallback.OrderClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-order", fallback = OrderClientFallback.class)
public interface OrderClient {

    @GetMapping("/api/order")
    String findBySn(@RequestParam(value = "sn") String sn);

}
