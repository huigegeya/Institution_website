package com.huige.Institution.constant;

/**
 * 返回状态码
 *
 * @author hying
 */
public class RespCode {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    public static final int BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 访问受限
     */
    public static final int FORBIDDEN = 403;

    /**
     * 资源未找到
     */
    public static final int NOT_FOUND = 404;

    /**
     * 不允许的http方法
     */
    public static final int BAD_METHOD = 405;

    /**
     * 资源冲突，或者资源被锁
     */
    public static final int CONFLICT = 409;

    /**
     * 不支持的数据或媒体类型
     */
    public static final int UNSUPPORTED_TYPE = 415;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 登录失败
     */
    public static final int LOGIN_FAILURE = 501;

    /**
     * 数据不存在
     */
    public static final int DATA_NOT_EXIST = 505;
}
