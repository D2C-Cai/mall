package com.d2c.order.business.client;

import com.d2c.order.business.client.fallback.MemberClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-member", fallback = MemberClientFallback.class)
public interface MemberClient {

    @GetMapping("/api/user/update/{id}")
    int updateNameById(@PathVariable(name = "id") Long id, @RequestParam(value = "username") String username);

}
