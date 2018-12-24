package com.d2c.member.mongodb.repository;

import com.d2c.member.mongodb.document.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserMongoRepository extends MongoRepository<UserMongo, Long> {

    List<UserMongo> findByUsername(String username);

}
