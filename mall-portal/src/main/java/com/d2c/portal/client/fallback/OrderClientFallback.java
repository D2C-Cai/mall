package com.d2c.portal.client.fallback;

import com.alibaba.fastjson.JSONObject;
import com.d2c.portal.client.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientFallback implements OrderClient {

    @Override
    public String findBySn(String sn) {
        System.out.println("OrderClientFallback中的降级方法");
        JSONObject obj = new JSONObject();
        obj.put("id", 9999L);
        obj.put("sn", "Q9999");
        obj.put("payAmount", "999");
        return obj.toJSONString();
    }

}
