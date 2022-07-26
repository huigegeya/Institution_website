package com.huige.Institution.service;

import com.huige.Institution.domain.entity.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> listPage();

    int add(Comment comment);

    int delete(Integer id);
}
