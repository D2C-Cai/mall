package com.d2c.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.d2c.portal.service.CreateOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateOrderCtrl {

    @Autowired
    private CreateOrderService createOrderService;

    @HystrixCommand(fallbackMethod = "findThingsFail")
    @RequestMapping(value = "/api/find/data", method = RequestMethod.GET)
    public JSONObject findThings(String username, String productSn, String orderSn) {
        return createOrderService.findThings(username, productSn, orderSn);
    }

    private JSONObject findThingsFail(String username, String productSn, String orderSn) {
        System.out.println("controller中的降级方法");
        JSONObject obj = new JSONObject();
        obj.put("code", -1);
        obj.put("msg", "抢购人数太多，您被挤出来了，请稍等重试！");
        return obj;
    }

}
