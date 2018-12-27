package com.d2c.order.business.dao;

import com.d2c.order.business.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface OrderMapper {

    Order findBySn(@Param("sn") String sn);

    int updateAmountBySn(@Param("sn") String sn, @Param("payAmount") BigDecimal payAmount);

}
