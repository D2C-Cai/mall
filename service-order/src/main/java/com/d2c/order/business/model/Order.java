package com.d2c.order.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Order implements Serializable {

    Long id;
    @NonNull
    String sn;
    BigDecimal payAmount;

}
