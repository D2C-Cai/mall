package com.d2c.member.business.dao;

import com.d2c.member.business.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByName(@Param("username") String username);

}
