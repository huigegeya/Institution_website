package com.huige.Institution.web.controller;

import com.huige.Institution.constant.Constants;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.domain.vo.LoggedInUser;
import com.huige.Institution.domain.vo.LoginForm;
import com.huige.Institution.service.ILoginService;
import com.huige.Institution.service.impl.TokenService;
import com.huige.Institution.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证
 *
 * @author hying
 */
@RestController
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginForm 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@Validated @RequestBody LoginForm loginForm) {
        // 生成令牌
        String token = loginService.login(loginForm.getUsername(), loginForm.getPassword(), loginForm.getCode(),
                loginForm.getRememberMe());
        AjaxResult result = AjaxResult.success();
        result.put(Constants.TOKEN, token);
        return result;
    }

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     */
    @GetMapping("/getLoggedInUserInfo")
    public AjaxResult getLoggedInUserInfo() {
        LoggedInUser loggedInUser = tokenService.getLoggedInUser(ServletUtil.getRequest());
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.USER_INFO, loggedInUser);
        return ajax;
    }
    @GetMapping("/getLoggedInUserInfo1")
    public AjaxResult getLoggedInUserInfo1() {
        LoggedInUser loggedInUser = tokenService.getLoggedInUser(ServletUtil.getRequest());
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.USER_INFO, loggedInUser);
        return ajax;
    }
    @GetMapping("/getLoggedInUserInfo2")
    public AjaxResult getLoggedInUserInfo2() {
        LoggedInUser loggedInUser = tokenService.getLoggedInUser(ServletUtil.getRequest());
        AjaxResult ajax = AjaxResult.success();
        ajax.put(Constants.USER_INFO, loggedInUser);
        return ajax;
    }
}
