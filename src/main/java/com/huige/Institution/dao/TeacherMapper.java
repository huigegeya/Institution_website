package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.SubjectInfo;
import com.huige.Institution.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("select * from t_teacher where username = #{username}")
    public List<User> selectByTeacherUsername(String username);

    @Update("update t_teacher set email = #{email},name = #{name},sex = #{sex} where id = #{id}")
    public int update(User user);

    // 更新用户密码
    @Update("update t_teacher set password = #{password} where id = #{id}")
    public int updatePwd(@Param("id") Integer id, @Param("password") String password);

    public List<SubjectInfo> selectMySubject(SubjectInfo subjectInfo);
}
