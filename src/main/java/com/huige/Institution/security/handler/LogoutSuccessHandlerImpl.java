package com.huige.Institution.security.handler;

import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.domain.vo.LoggedInUser;
import com.huige.Institution.service.impl.TokenService;
import com.huige.Institution.util.ServletUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author jeethink 官方网站:www.jeethink.vip
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoggedInUser loggedInUser = tokenService.getLoggedInUser(request);
        if (loggedInUser != null) {
            // 删除用户缓存记录
            tokenService.delLoggedInUser(loggedInUser.getUuid());
        }
        ServletUtil.renderString(response, new JSONObject(AjaxResult.success("退出成功")).toString());
    }
}
