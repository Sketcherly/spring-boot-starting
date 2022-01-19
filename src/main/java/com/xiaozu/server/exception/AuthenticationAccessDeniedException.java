package com.xiaozu.server.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 没有相关功能的权限
 *
 * @author dongpo.li
 * @date 2022/1/19
 */
public class AuthenticationAccessDeniedException extends AuthenticationException {
    public AuthenticationAccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationAccessDeniedException(String msg) {
        super(msg);
    }
}
