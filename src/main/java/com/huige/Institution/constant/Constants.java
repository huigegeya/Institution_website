package com.huige.Institution.constant;

/**
 * 通用常量信息
 *
 * @author hying
 */
public class Constants {
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_code:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_USER_KEY = "user:";

    /**
     * 防重复提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 用户信息
     */
    public static final String USER_INFO = "userInfo";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 登录用户 jwt payload key
     */
    public static final String LOGIN_USER_UUID_KEY = "login_user_uuid_key";
    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 1秒钟的毫秒数
     */
    public static final long MILLIS_OF_SECOND = 1000;

    /**
     * 一分钟的毫秒数
     */
    public static final long MILLIS_OF_MINUTE = 60 * MILLIS_OF_SECOND;

    /**
     * 一小时的毫秒数
     */
    public static final long MILLIS_OF_HOUR = 60 * MILLIS_OF_MINUTE;

    /**
     * 一天的毫秒数
     */
    public static final long MILLIS_OF_DAY = 24 * MILLIS_OF_HOUR;
}
