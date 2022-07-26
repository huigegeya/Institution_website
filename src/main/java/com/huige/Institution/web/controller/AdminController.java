package com.huige.Institution.web.controller;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.domain.entity.User;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.domain.vo.PasswordData;
import com.huige.Institution.exception.CustomException;
import com.huige.Institution.service.IAdminService;
import com.huige.Institution.util.SecurityUtil;
import com.huige.Institution.validation.ValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/blog/admin")
@Validated
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @GetMapping("/{username}")
    public AjaxResult get(@NotNull @PathVariable String username) {
        User user = adminService.getByAdminUsername(username);
        return AjaxResult.selectResult(user,"管理员");
    }

    @PutMapping("/savePasswordA")
    public AjaxResult updatePassword(@Validated(ValidGroup.Crud.Update.class) @RequestBody PasswordData passwordData) {
        User user = adminService.getByAdminUsername(SecurityUtil.getUsername());
        if (!SecurityUtil.matchesPassword(passwordData.getPassword(),user.getPassword())){
            throw new CustomException("输入的原密码有误！", RespCode.FORBIDDEN);
        }else if(passwordData.getPassword().equals(passwordData.getNewpassword1())){
            throw new CustomException("输入的新密码不能和原密码相同！", RespCode.FORBIDDEN);
        }else if(!passwordData.getNewpassword1().equals(passwordData.getNewpassword2())){
            throw new CustomException("新密码确认有误，请重新确认密码！", RespCode.FORBIDDEN);
        }
        return AjaxResult.updateResult(adminService.updatePwd(user.getId(), passwordData.getNewpassword2()), "密码修改");
    }


}
