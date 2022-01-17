package com.xiaozu.server.configuration.jwt;

import com.xiaozu.server.domain.vo.BasicResponseObject;
import com.xiaozu.server.utils.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
public class JwtAuthenticationException extends AuthenticationException {

    public static final BasicResponseObject<?> NON_AUTH_RESPONSE = BasicResponseObject.fail(203, "鉴权失败");

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

    public JwtAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
