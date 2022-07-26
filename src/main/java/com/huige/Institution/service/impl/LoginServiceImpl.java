package com.huige.Institution.service.impl;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.domain.vo.LoggedInUser;
import com.huige.Institution.exception.CustomException;
import com.huige.Institution.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static com.huige.Institution.constant.Constants.MILLIS_OF_DAY;
import static com.huige.Institution.constant.Constants.MILLIS_OF_MINUTE;

/**
 * 登录校验方法
 *
 * @author hying
 */
@Service
public class LoginServiceImpl implements ILoginService {
    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录验证
     *
     * @param username   用户名
     * @param password   密码
     * @param code       验证码
     * @param rememberMe "记住我"天数
     * @return 结果
     */
    public String login(String username, String password, String code, Integer rememberMe) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new CustomException("密码不正确", RespCode.LOGIN_FAILURE);
            } else {
                throw new CustomException(e.getMessage(), RespCode.LOGIN_FAILURE);
            }
        }
        LoggedInUser loggedInUser = (LoggedInUser) authentication.getPrincipal();
        loggedInUser.setLoginTime(System.currentTimeMillis());
        // 生存时间（毫秒）
        long ttlMillis = expireTime * MILLIS_OF_MINUTE + rememberMe * MILLIS_OF_DAY;
        loggedInUser.setExpireTime(loggedInUser.getLoginTime() + ttlMillis);
        // 生成token
        return tokenService.createUserToken(loggedInUser);
    }
}
