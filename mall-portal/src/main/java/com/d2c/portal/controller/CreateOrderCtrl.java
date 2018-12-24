package com.d2c.portal.controller;

import com.alibaba.fastjson.JSONObject;
import com.d2c.portal.service.CreateOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateOrderCtrl {

    @Autowired
    private CreateOrderService createOrderService;

    @RequestMapping(value = "/api/find/data", method = RequestMethod.GET)
    public JSONObject findThings(String username, String productSn, String orderSn) {
        return createOrderService.findThings(username, productSn, orderSn);
    }

}
