package com.huige.Institution.domain.vo;

import com.huige.Institution.validation.EnumValue;
import org.hibernate.validator.constraints.Length;

/**
 * 用户登录表单数据
 *
 * @author hying75
 */
public class LoginForm {
    /**
     * 用户名
     */
    @Length(min = 3, max = 30, message = "用户名：3-30个字符")
    private String username;
    /**
     * 用户密码
     */
    @Length(min = 6, max = 10, message = "密码：6-10个字符")
    private String password;
    /**
     * 验证码
     */
    private String code;
    /**
     * "记住我"天数
     */
    @EnumValue(intValues = {0, 30, 90}, message = "\"记住我\"天数必须为指定值")
    private Integer rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Integer rememberMe) {
        this.rememberMe = rememberMe;
    }
}
