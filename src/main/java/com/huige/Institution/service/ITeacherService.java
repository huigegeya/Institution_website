package com.huige.Institution.service;

import com.huige.Institution.domain.entity.SubjectInfo;
import com.huige.Institution.domain.entity.User;

import java.util.List;

public interface ITeacherService {

    public User getByTeacherUsername(String username);

    public int update(User user);

    public int updatePwd(Integer id, String password);

    List<SubjectInfo> listMySubjectInfo(SubjectInfo subjectInfo);
}
