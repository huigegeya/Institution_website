package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.SubjectInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SubjectInfoMapper {
    @Select("SELECT * FROM subjectinfo WHERE subjectId = #{subjectId}")
    public SubjectInfo selectById(Integer subjectId);

    public List<SubjectInfo> select(SubjectInfo subjectInfo);

    public int insert(SubjectInfo subjectInfo);

    // 通过id删除文章
    @Delete("DELETE FROM subjectinfo WHERE subjectId = #{subjectId}")
    public int delete(Integer subjectId);

    // 更新文章
    public int update(SubjectInfo subjectInfo);


    @Update("update subjectinfo set peopleNum = peopleNum+1 where subjectId = #{subjectId}")
    public int applyUpdate(Integer subjectId);
}
