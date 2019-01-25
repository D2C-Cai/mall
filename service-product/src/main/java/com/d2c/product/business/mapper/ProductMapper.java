package com.d2c.product.business.mapper;

import com.d2c.product.business.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface ProductMapper {

    Product findBySn(@Param("sn") String sn);

    int updatePriceById(@Param("id") Long id, @Param("price") BigDecimal price);

}
