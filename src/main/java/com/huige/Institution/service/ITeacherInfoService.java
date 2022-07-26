package com.huige.Institution.service;

import com.huige.Institution.domain.entity.TeacherInfo;

import java.util.List;

public interface ITeacherInfoService {
    int add(TeacherInfo teacherInfo);

    List<TeacherInfo> listPage();

    int delete(Integer teacherId);

    int update(TeacherInfo teacherInfo);
}
