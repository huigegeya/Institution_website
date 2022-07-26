package com.huige.Institution.dao;

import com.huige.Institution.domain.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Comment Mapper
 *
 * @author hying
 */
@Mapper
public interface CommentMapper {
    // 根据id查询
    @Select("select * from t_comment where id = #{id}")
    public Comment selectById(Integer id);

    // 倒序查询某个文章的评论
    @Select("SELECT * FROM t_comment ORDER BY created DESC")
    public List<Comment> select();

    // 统计评论数量
    @Select("SELECT COUNT(*) FROM t_comment")
    public int count();

    // 添加评论
    @Insert("INSERT INTO t_comment (created,author,ip,content)" +
            " VALUES ( #{created},#{author},#{ip},#{content})")
    public int insert(Comment comment);

    // 根据文章id删除评论
    @Delete("DELETE FROM t_comment WHERE id=#{id}")
    public int delete(Integer id);


}

