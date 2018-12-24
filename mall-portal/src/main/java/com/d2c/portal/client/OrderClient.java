package com.d2c.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-order")
public interface OrderClient {

    @GetMapping("/api/order")
    String findBySn(@RequestParam(value = "sn") String sn);

}
