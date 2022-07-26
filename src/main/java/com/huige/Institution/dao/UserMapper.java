package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.Apply;
import com.huige.Institution.domain.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User Mapper
 *
 * @author hying
 */
@Mapper
public interface UserMapper {
    // 根据用户名查询用户
    @Select("select * from t_user where username = #{username}")
    public List<User> selectByUsername(String username);

    @Select("select * from apply where stuId = #{stuId} and subjectId = #{subjectId}")
    public List<Apply> selectApply(Apply apply);

    @Insert("INSERT INTO apply (stuId,subjectId,teacher,subjectName,startTime,stuName)" +
            " VALUES (#{stuId}, #{subjectId},#{teacher},#{subjectName},#{startTime},#{stuName})")
    public int apply(Apply apply);

    public List<Apply> selectMyApply(Apply apply);

    @Delete("DELETE FROM apply WHERE subjectId = #{subjectId}")
    public void deleteBySubjectId(Integer subjectId);



    // 更新用户
    @Update("update t_user set email = #{email},name = #{name},sex = #{sex} where id = #{id}")
    public int update(User user);

    // 更新用户密码
    @Update("update t_user set password = #{password} where id = #{id}")
    public int updatePwd(@Param("id") Integer id, @Param("password") String password);

    // 查询用户总数
    @Select("select count(*) from t_user")
    public int count();
}

