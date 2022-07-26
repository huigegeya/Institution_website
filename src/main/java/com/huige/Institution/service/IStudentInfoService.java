package com.huige.Institution.service;

import com.huige.Institution.domain.entity.StudentInfo;

import java.util.List;

public interface IStudentInfoService {
    int add(StudentInfo studentInfo);

    List<StudentInfo> listPage();

    int delete(Integer stuId);

    int update(StudentInfo studentInfo);
}
