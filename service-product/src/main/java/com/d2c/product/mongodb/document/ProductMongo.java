package com.d2c.product.mongodb.document;

import com.d2c.product.business.model.Product;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "user")
public class ProductMongo {

    Long id;

    @NonNull
    String sn;

    BigDecimal price;

    public ProductMongo() {
    }

    public ProductMongo(Product product) {
        this();
        BeanUtils.copyProperties(product, this);
    }

}
