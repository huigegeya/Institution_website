package com.huige.Institution.web.controller;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.exception.CustomException;
import com.huige.Institution.service.IRegisterService;
import com.huige.Institution.service.ITeacherService;
import com.huige.Institution.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class RegisterController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IRegisterService registerService;

    @PostMapping("/register")
    public AjaxResult register(@Validated @RequestBody User user) {
        if (!(userService.getByUsername(user.getUsername()) == null)) {
            throw new CustomException("用户名已存在，无法注册！", RespCode.FORBIDDEN);
        }
        return AjaxResult.updateResult(registerService.insert(user), "注册");
    }

    @PostMapping("/teacherRegister")
    public AjaxResult teacherRegister(@Validated @RequestBody User user) {
        if (!(teacherService.getByTeacherUsername(user.getUsername()) == null)) {
            throw new CustomException("用户名已存在，无法注册！", RespCode.FORBIDDEN);
        }
        return AjaxResult.updateResult(registerService.insert1(user), "注册");
    }

}
