package com.d2c.product.elasticsearch.repository;

import com.d2c.product.elasticsearch.document.ProductSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearch, Long> {

    List<ProductSearch> findBySn(String sn);

}
