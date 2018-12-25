package com.d2c.portal.client;

import com.d2c.portal.client.fallback.MemberClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-member", fallback = MemberClientFallback.class)
public interface MemberClient {

    @GetMapping("/api/user")
    String findByName(@RequestParam(value = "username") String username);

}
