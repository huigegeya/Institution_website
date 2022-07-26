package com.huige.Institution.service.impl;

import com.huige.Institution.dao.AdminMapper;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.service.IAdminService;
import com.huige.Institution.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public User getByAdminUsername(String username) {
        List<User> users = adminMapper.selectByAdminUsername(username);
        if (CollectionUtils.isEmpty(users))
            return null;
        return users.get(0);
    }

    @Override
    public int updatePwd(Integer id, String password) {
        return adminMapper.updatePwd(id, SecurityUtil.encryptPassword(password));
    }
}
