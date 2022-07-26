package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.StudentInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentInfoMapper {

    public int insert(StudentInfo studentInfo);

    @Select("SELECT * FROM studentinfo")
    public List<StudentInfo> select();

    @Delete("DELETE FROM studentinfo WHERE stuId = #{stuId}")
    public int delete(Integer stuId);

    // 更新文章
    public int update(StudentInfo studentInfo);
}
