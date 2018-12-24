package com.d2c.order.elasticsearch.repository;


import com.d2c.order.elasticsearch.document.OrderSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface OrderSearchRepository extends ElasticsearchRepository<OrderSearch, Long> {

    List<OrderSearch> findBySn(String sn);

}
