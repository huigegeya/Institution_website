package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    // 添加用户
    @Insert("INSERT INTO t_user (username,password,email,created,valid,sex,name)" +
            " VALUES (#{username}, #{password},#{email},#{created},#{valid},#{sex},#{name})")
    public int insert(User user);

    @Insert("INSERT INTO t_teacher (username,password,email,created,valid,sex,name)" +
            " VALUES (#{username}, #{password},#{email},#{created},#{valid},#{sex},#{name})")
    public int insert1(User user);
}
