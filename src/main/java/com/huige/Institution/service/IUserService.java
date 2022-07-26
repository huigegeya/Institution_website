package com.huige.Institution.service;

import com.huige.Institution.domain.entity.Apply;
import com.huige.Institution.domain.entity.User;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author hying
 */
public interface IUserService {
    // 根据用户名查询用户
    public User getByUserUsername(String username);

    public User getByUsername(String username);

    // 更新用户
    public int update(User user);

    // 更新用户密码
    public int updatePwd(Integer id, String password);

    int apply(Apply apply);


    List<Apply> myApply(Apply apply);

}

