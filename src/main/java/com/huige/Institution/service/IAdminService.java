package com.huige.Institution.service;

import com.huige.Institution.domain.entity.User;

public interface IAdminService {

    public User getByAdminUsername(String username);

    public int updatePwd(Integer id, String password);
}
