package com.huige.Institution.service.impl;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.domain.vo.LoggedInUser;
import com.huige.Institution.exception.CustomException;
import com.huige.Institution.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 用户验证服务实现类
 *
 * @author hying
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    // 登录类型
    private static final Integer COMMON_LOGIN_TYPE = 0;

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new CustomException("用户不存在", RespCode.LOGIN_FAILURE);
        } else if (user.getId() == 0) {
            log.info("登录用户：{} 已被停用.", username);
            throw new CustomException("用户已被停用", RespCode.LOGIN_FAILURE);
        }
        LoggedInUser loggedInUser = new LoggedInUser(UUID.randomUUID().toString(), user.getId(), username, user.getPassword(), COMMON_LOGIN_TYPE);
        return loggedInUser;
    }
}
