package com.xiaozu.server.configuration;

import com.xiaozu.server.domain.vo.BasicResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dongpo.li
 * @date 2020/7/20
 */
@Component
@RestControllerAdvice
public class GlobalRestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public BasicResponseObject<?> handleBusinessException(AuthenticationException e) {
        return BasicResponseObject.fail(203, "鉴权失败");
    }

    @ExceptionHandler(Throwable.class)
    public BasicResponseObject<?> handleException(Throwable e) {
        logger.error("服务未知异常", e);
        return BasicResponseObject.fail(10000, "服务未知异常");
    }

    @ExceptionHandler(ServletException.class)
    public BasicResponseObject<?> handleNoHandlerFoundException(ServletException e) {

        if (e instanceof NoHandlerFoundException) {
            return BasicResponseObject.fail(HttpServletResponse.SC_NOT_FOUND, "404 not found");
        }
        if (e instanceof HttpRequestMethodNotSupportedException) {
            String message = "不支持的请求方式,请检查GET/POST";
            return BasicResponseObject.fail(HttpServletResponse.SC_METHOD_NOT_ALLOWED, message);
        }
        if (e instanceof HttpMediaTypeNotSupportedException) {
            String message = "不支持的参数提交方式,请检查Json/FormEncode";
            return BasicResponseObject.fail(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, message);
        }

        return BasicResponseObject.fail(10000, "服务未知异常");
    }

}
