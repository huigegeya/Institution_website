package com.huige.Institution.service;

/**
 * 用户登录接口
 *
 * @author hying
 */
public interface ILoginService {
    /**
     * 登录验证
     *
     * @param username   用户名
     * @param password   密码
     * @param code       验证码
     * @param rememberMe "记住我"天数
     * @return jwt的token字符串
     */
    public String login(String username, String password, String code, Integer rememberMe);
}
