package com.d2c.product.business.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Product implements Serializable {

    Long id;
    @NonNull
    String sn;
    BigDecimal price;

}
