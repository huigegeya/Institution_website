package com.huige.Institution.web.exception;

import com.huige.Institution.constant.RespCode;
import com.huige.Institution.domain.vo.AjaxResult;
import com.huige.Institution.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 *
 * @author jeethink
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult businessException(CustomException e) {
        log.error(e.getMessage(), e);
        if (e.getCode() == null) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public AjaxResult handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(RespCode.NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAuthorizationException(AccessDeniedException e) {
        log.error(e.getMessage());
        return AjaxResult.error(RespCode.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)// 未使用
    public AjaxResult handleAccountExpiredException(AccountExpiredException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(RespCode.UNAUTHORIZED, "账号已过期");
    }

    @ExceptionHandler(UsernameNotFoundException.class)// 未使用
    public AjaxResult handleUsernameNotFoundException(UsernameNotFoundException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(RespCode.UNAUTHORIZED, "账号不存在");
    }

    /**
     * 自定义数据验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(RespCode.BAD_REQUEST, message);
    }

    /**
     * 自定义数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error(RespCode.BAD_REQUEST, message);
    }

    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }
}
