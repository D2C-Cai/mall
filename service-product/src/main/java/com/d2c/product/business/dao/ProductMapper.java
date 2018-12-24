package com.d2c.product.business.dao;

import com.d2c.product.business.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {

    Product findBySn(@Param("sn") String sn);

}
