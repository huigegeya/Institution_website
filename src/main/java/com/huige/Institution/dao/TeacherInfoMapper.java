package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.TeacherInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherInfoMapper {

    public int insert(TeacherInfo teacherInfo);

    @Select("SELECT * FROM teacherinfo")
    public List<TeacherInfo> select();

    @Delete("DELETE FROM teacherinfo WHERE teacherId = #{teacherId}")
    public int delete(Integer teacherId);

    // 更新文章
    public int update(TeacherInfo teacherInfo);
}
