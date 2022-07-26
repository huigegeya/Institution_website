package com.huige.Institution.service.impl;

import com.huige.Institution.dao.CommentMapper;
import com.huige.Institution.domain.entity.Comment;
import com.huige.Institution.service.ICommentService;
import com.huige.Institution.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listPage() {
        // 开始分页
        PageUtil.start();
        return commentMapper.select();
    }

    @Override
    public int add(Comment comment) {
        int rows = commentMapper.insert(comment);
        return rows;
    }

    @Override
    public int delete(Integer id) {
        return commentMapper.delete(id);
    }
}
