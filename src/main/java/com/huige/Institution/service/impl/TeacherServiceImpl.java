package com.huige.Institution.service.impl;

import com.huige.Institution.dao.TeacherMapper;
import com.huige.Institution.domain.entity.SubjectInfo;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.service.ITeacherService;
import com.huige.Institution.util.PageUtil;
import com.huige.Institution.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public User getByTeacherUsername(String username) {
        List<User> users = teacherMapper.selectByTeacherUsername(username);
        if (CollectionUtils.isEmpty(users))
            return null;
        return users.get(0);
    }


    @Override
    public int update(User user) {
        return teacherMapper.update(user);
    }

    @Override
    public int updatePwd(Integer id, String password) {
        return teacherMapper.updatePwd(id, SecurityUtil.encryptPassword(password));
    }

    @Override
    public List<SubjectInfo> listMySubjectInfo(SubjectInfo subjectInfo) {
        PageUtil.start();
        System.out.println(subjectInfo.getSubjectName());
        subjectInfo.setTeacher(teacherMapper.selectByTeacherUsername(SecurityUtil.getUsername()).get(0).getName());
        List<SubjectInfo> list = teacherMapper.selectMySubject(subjectInfo);
        return list;
    }
}
