package com.huige.Institution.service.impl;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.dao.*;
import com.huige.Institution.domain.entity.Apply;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.exception.CustomException;
import com.huige.Institution.service.IUserService;
import com.huige.Institution.util.PageUtil;
import com.huige.Institution.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户服务实现类
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SubjectInfoMapper subjectInfoMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public User getByUserUsername(String username) {
        List<User> users = userMapper.selectByUsername(username);
        if (CollectionUtils.isEmpty(users))
            return null;
        return users.get(0);
    }

    @Override
    public User getByUsername(String username) {
        List<User> users = userMapper.selectByUsername(username);
        if (CollectionUtils.isEmpty(users)) {
            users = adminMapper.selectByAdminUsername(username);
        }
        if (CollectionUtils.isEmpty(users)) {
            users = teacherMapper.selectByTeacherUsername(username);
        }
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int updatePwd(Integer id, String password) {
        return userMapper.updatePwd(id, SecurityUtil.encryptPassword(password));
    }


    @Transactional
    @Override
    public int apply(Apply apply) {
        List<User> users = userMapper.selectByUsername(SecurityUtil.getUsername());
        apply.setStuName(SecurityUtil.getUsername());
        apply.setStuId(users.get(0).getId());
        if (userMapper.selectApply(apply).size() > 0)
            throw new CustomException("您已报名该课程!", RespCode.FORBIDDEN);
        int rows = userMapper.apply(apply);
        subjectInfoMapper.applyUpdate(apply.getSubjectId());
        return rows;
    }


    @Override
    public List<Apply> myApply(Apply apply) {
        PageUtil.start();
        apply.setStuName(SecurityUtil.getUsername());
        List<Apply> applyList = userMapper.selectMyApply(apply);
        return applyList;
    }
}
