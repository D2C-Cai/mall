package com.d2c.member.business.service;

import com.d2c.member.business.model.User;
import com.d2c.member.elasticsearch.document.UserSearch;
import com.d2c.member.mongodb.document.UserMongo;

import java.util.List;

public interface UserService {

    User findByName(String username);

    User findCacheByName(String username);

    List<UserMongo> findMongoByName(String username);

    List<UserSearch> findSearchByName(String username);

    int updatePasswdById(Long id, String password);

}
