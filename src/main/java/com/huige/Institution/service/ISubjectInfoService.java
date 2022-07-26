package com.huige.Institution.service;

import com.huige.Institution.domain.entity.SubjectInfo;

import java.util.List;

public interface ISubjectInfoService {
    List<SubjectInfo> listPage(SubjectInfo subjectInfo);

    SubjectInfo getById(Integer subjectId);

    int delete(Integer[] ids);

    int update(SubjectInfo subjectInfo);

    int add(SubjectInfo subjectInfo);
}
