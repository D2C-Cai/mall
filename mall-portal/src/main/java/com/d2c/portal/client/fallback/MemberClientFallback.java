package com.d2c.portal.client.fallback;

import com.alibaba.fastjson.JSONObject;
import com.d2c.portal.client.MemberClient;
import org.springframework.stereotype.Component;

@Component
public class MemberClientFallback implements MemberClient {

    @Override
    public String findByName(String username) {
        System.out.println("MemberClientFallback中的降级方法");
        JSONObject obj = new JSONObject();
        obj.put("id", 9999L);
        obj.put("username", "admin");
        obj.put("password", "admin");
        return obj.toJSONString();
    }

}
