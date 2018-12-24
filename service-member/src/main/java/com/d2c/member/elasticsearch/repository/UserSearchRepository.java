package com.d2c.member.elasticsearch.repository;

import com.d2c.member.elasticsearch.document.UserSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserSearchRepository extends ElasticsearchRepository<UserSearch, Long> {

    List<UserSearch> findByUsername(String username);

}
