package com.d2c.product.elasticsearch.document;


import com.d2c.product.business.model.Product;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Document(indexName = "d2cmall-index", type = "product")
public class ProductSearch implements Serializable {

    Long id;

    @NonNull
    @Field(type = FieldType.Keyword)
    String sn;

    BigDecimal price;


    public ProductSearch() {
    }

    public ProductSearch(Product product) {
        this();
        BeanUtils.copyProperties(product, this);
    }

}
