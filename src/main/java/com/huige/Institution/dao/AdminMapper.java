package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from t_admin where username = #{username}")
    public List<User> selectByAdminUsername(String username);

    @Update("update t_admin set password = #{password} where id = #{id}")
    public int updatePwd(@Param("id") Integer id, @Param("password") String password);
}
