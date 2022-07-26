package com.huige.Institution.web.controller;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.domain.entity.Apply;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.domain.vo.PasswordData;
import com.huige.Institution.exception.CustomException;
import com.huige.Institution.service.IUserService;
import com.huige.Institution.util.SecurityUtil;
import com.huige.Institution.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/blog/student")
@Validated
public class StudentController {
    @Autowired
    private IUserService userService;

    @GetMapping("/{username}")
    public AjaxResult get(@NotNull @PathVariable String username) {
        User user = userService.getByUserUsername(username);
        return AjaxResult.selectResult(user,"学生");
    }

    @PutMapping("/savepassword")
    public AjaxResult updatepassword(@Validated(ValidGroup.Crud.Update.class) @RequestBody PasswordData passwordData) {
        User user = userService.getByUserUsername(passwordData.getUsername());
        if (!SecurityUtil.matchesPassword(passwordData.getPassword(),user.getPassword())){
            throw new CustomException("输入的原密码有误！", RespCode.FORBIDDEN);
        }else if(passwordData.getPassword().equals(passwordData.getNewpassword1())){
            throw new CustomException("输入的新密码不能和原密码相同！", RespCode.FORBIDDEN);
        }else if(!passwordData.getNewpassword1().equals(passwordData.getNewpassword2())){
            throw new CustomException("新密码确认有误，请重新确认密码！", RespCode.FORBIDDEN);
        }
        return AjaxResult.updateResult(userService.updatePwd(user.getId(), passwordData.getNewpassword2()), "密码修改");
    }

    @GetMapping("/getuserinfo/{username}")
    public AjaxResult get1(@NotNull @PathVariable String username) {
        User user = userService.getByUserUsername(username);
        return AjaxResult.success(user);
    }

    @PutMapping("/saveUser")
    public AjaxResult updateUser(@Validated(ValidGroup.Crud.Update.class) @RequestBody User user) {
        return AjaxResult.updateResult(userService.update(user), "修改");
    }

    @PostMapping("/apply")
    public AjaxResult apply(@Validated(ValidGroup.Crud.Create.class) @RequestBody Apply apply) {
        return AjaxResult.updateResult(userService.apply(apply), "报名");
    }


    @GetMapping("/myapply")
    public AjaxResult myApply(Apply apply) {
        return AjaxResult.pageResult(userService.myApply(apply));
    }
}
