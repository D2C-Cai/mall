package com.d2c.order.business.client.fallback;


import com.d2c.order.business.client.MemberClient;
import org.springframework.stereotype.Component;

@Component
public class MemberClientFallback implements MemberClient {

    @Override
    public int updateNameById(Long id, String username) {
        System.out.println("MemberClientFallback中的降级方法");
        throw new RuntimeException("updateNameById更新失败");
    }

}
