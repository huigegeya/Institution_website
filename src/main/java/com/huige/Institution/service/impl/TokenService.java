package com.huige.Institution.service.impl;

import cn.hutool.core.util.StrUtil;
import com.huige.Institution.constant.Constants;
import com.huige.Institution.domain.vo.LoggedInUser;
import com.huige.Institution.util.ServletUtil;
import com.huige.Institution.util.ip.AddressUtil;
import com.huige.Institution.util.ip.IpUtil;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.huige.Institution.constant.Constants.MILLIS_OF_MINUTE;

/**
 * token验证处理
 *
 * @author hying
 */
@Component
public class TokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;


    private static final long MILLIS_OF_REFRESH = 20 * MILLIS_OF_MINUTE;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取用户登录信息
     *
     * @return 用户登录信息
     */
    public LoggedInUser getLoggedInUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StrUtil.isNotEmpty(token)) {
            Claims claims = parseToken(token);
            if (claims == null)
                return null;
            String userUUID = (String) claims.get(Constants.LOGIN_USER_UUID_KEY);
            String userRedisKey = getUserRedisKey(userUUID);
            LoggedInUser user = (LoggedInUser) redisTemplate.opsForValue().get(userRedisKey);
            return user;
        }
        return null;
    }

    /**
     * 删除用户登录信息
     */
    public void delLoggedInUser(String userUUID) {
        if (StrUtil.isNotEmpty(userUUID)) {
            String userKey = getUserRedisKey(userUUID);
            redisTemplate.delete(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param loggedInUser 登录用户信息
     * @return 令牌
     */
    public String createUserToken(LoggedInUser loggedInUser) {
        setUserAgent(loggedInUser);
        // 将登录用户信息存入redis
        redisTemplate.opsForValue().set(getUserRedisKey(loggedInUser.getUuid()), loggedInUser, loggedInUser.getExpireTime() - loggedInUser.getLoginTime(), TimeUnit.MILLISECONDS);
        // jwt token携带的数据-->用户的uuid
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_UUID_KEY, loggedInUser.getUuid());
        return createToken(claims);
    }

    /**
     * 检查是否需要刷新token，距到期时间不足20分钟，自动刷新缓存
     *
     * @param loggedInUser 登录用户信息
     */
    public void checkRefresh(LoggedInUser loggedInUser) {
        long expireTime = loggedInUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_OF_REFRESH) {
            refreshToken(loggedInUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loggedInUser 登录用户信息
     */
    public void refreshToken(LoggedInUser loggedInUser) {
        loggedInUser.setLoginTime(System.currentTimeMillis());
        loggedInUser.setExpireTime(loggedInUser.getLoginTime() + expireTime * MILLIS_OF_MINUTE);
        // 根据userKey刷新缓存
        String userRedisKey = getUserRedisKey(loggedInUser.getUuid());
        redisTemplate.opsForValue().set(userRedisKey, loggedInUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param loggedInUser 用户登录信息
     */
    public void setUserAgent(LoggedInUser loggedInUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        String ip = IpUtil.getIpAddr(ServletUtil.getRequest());
        loggedInUser.setIpAddress(ip);
        loggedInUser.setLoginLocation(AddressUtil.getRealAddressByIP(ip));
        loggedInUser.setBrowser(userAgent.getBrowser().getName());
        loggedInUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    public String createToken(Map<String, Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        System.out.println(token);
        if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getUserRedisKey(String uuid) {
        return Constants.LOGIN_USER_KEY + uuid;
    }
}
