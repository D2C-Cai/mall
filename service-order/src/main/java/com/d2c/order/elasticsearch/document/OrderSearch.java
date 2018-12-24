package com.d2c.order.elasticsearch.document;


import com.d2c.order.business.model.Order;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Document(indexName = "d2cmall-index", type = "order")
public class OrderSearch implements Serializable {

    Long id;

    @NonNull
    @Field(type = FieldType.Keyword)
    String sn;

    BigDecimal payAmount;


    public OrderSearch() {
    }

    public OrderSearch(Order order) {
        this();
        BeanUtils.copyProperties(order, this);
    }

}
