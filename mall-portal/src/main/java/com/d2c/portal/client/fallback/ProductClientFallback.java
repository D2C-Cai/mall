package com.d2c.portal.client.fallback;

import com.alibaba.fastjson.JSONObject;
import com.d2c.portal.client.ProductClient;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient {

    @Override
    public String findBySn(String sn) {
        System.out.println("ProductClientFallback中的降级方法");
        JSONObject obj = new JSONObject();
        obj.put("id", 9999L);
        obj.put("sn", "SN9999");
        obj.put("price", "999");
        return obj.toJSONString();
    }

}
