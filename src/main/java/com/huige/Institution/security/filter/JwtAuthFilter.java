package com.huige.Institution.security.filter;

import com.huige.Institution.domain.vo.LoggedInUser;
import com.huige.Institution.service.impl.TokenService;
import com.huige.Institution.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * token过滤器 验证token有效性
 *
 * @author hying
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        LoggedInUser loggedInUser = tokenService.getLoggedInUser(request);
        if (loggedInUser != null && SecurityUtil.getAuthentication() == null) {
            // 登录后，每次访问受限资源都会执行该代码，将认证信息放入Security上下文，后面的过滤器就会放行
            // Session已关闭，因此认证信息不会保存，每次都要再放入
            System.out.println("放入认证信息");
            tokenService.checkRefresh(loggedInUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loggedInUser, null, loggedInUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
