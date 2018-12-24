package com.d2c.order.mongodb.document;

import com.d2c.order.business.model.Order;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "user")
public class OrderMongo {

    Long id;

    @NonNull
    String sn;

    BigDecimal payAmount;

    public OrderMongo() {
    }

    public OrderMongo(Order order) {
        this();
        BeanUtils.copyProperties(order, this);
    }

}
