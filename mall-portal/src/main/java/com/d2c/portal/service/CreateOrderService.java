package com.d2c.portal.service;

import com.alibaba.fastjson.JSONObject;

public interface CreateOrderService {

    JSONObject findThings(String username, String productSn, String orderSn);

}
