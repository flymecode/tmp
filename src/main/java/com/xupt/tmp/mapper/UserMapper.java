package com.xupt.tmp.mapper;

import com.xupt.tmp.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    int insert(User user);

    @Select({"select * from user where `username` =  #{username}"})
    User selectByUsername(@Param("username") String username);

    @Select({"select id from user where (LOWER(`username`) = LOWER(#{name}) or LOWER(`email`) = LOWER(#{name}) or LOWER(`name`) = LOWER(#{name}))"})
    String getIdByName(@Param("name") String name);


}
