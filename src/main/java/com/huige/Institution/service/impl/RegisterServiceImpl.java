package com.huige.Institution.service.impl;

import com.huige.Institution.dao.RegisterMapper;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.service.IRegisterService;
import com.huige.Institution.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public int insert(User user) {
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        user.setCreated(new Date());
        user.setValid(1);
        return registerMapper.insert(user);
    }

    @Override
    public int insert1(User user) {
        user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        user.setCreated(new Date());
        user.setValid(1);
        return registerMapper.insert1(user);
    }
}
