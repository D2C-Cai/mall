package com.d2c.member.business.mapper;

import com.d2c.member.business.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByName(@Param("username") String username);

    int updatePasswdById(@Param("id") Long id, @Param("password") String password);

}
