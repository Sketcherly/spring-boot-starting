package com.xiaozu.server.configuration;

import com.xiaozu.server.exception.AuthenticationAccessDeniedException;
import com.xiaozu.server.domain.vo.BasicResponseObject;
import com.xiaozu.server.exception.BasicResponseStatus;
import com.xiaozu.server.exception.BusinessException;
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

/**
 * @author dongpo.li
 * @date 2020/7/20
 */
@Component
@RestControllerAdvice
public class GlobalRestExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public BasicResponseObject<?> handleAuthenticationException(AuthenticationException e) {
        if (e instanceof AuthenticationAccessDeniedException) {
            return BasicResponseObject.fail(BasicResponseStatus.HTTP_FORBIDDEN);
        }

        return BasicResponseObject.fail(BasicResponseStatus.HTTP_UNAUTHORIZED);
    }

    @ExceptionHandler(BusinessException.class)
    public BasicResponseObject<?> handleBusinessException(BusinessException e) {
        if (BasicResponseStatus.HTTP_INTERNAL_SERVER_ERROR.equals(e.getStatus())) {
            logger.error(e.getMessage(), e);
        } else {
            // 其他异常不打异常栈,属于可预知错误,以前端处理为主
            logger.error(e.getMessage());
        }
        return BasicResponseObject.fail(BasicResponseStatus.HTTP_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public BasicResponseObject<?> handleException(Throwable e) {
        logger.error("服务未知异常", e);
        return BasicResponseObject.fail(BasicResponseStatus.HTTP_INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServletException.class)
    public BasicResponseObject<?> handleNoHandlerFoundException(ServletException e) {

        if (e instanceof NoHandlerFoundException) {
            return BasicResponseObject.fail(BasicResponseStatus.HTTP_NOT_FOUND);
        }
        if (e instanceof HttpRequestMethodNotSupportedException) {
            // 不支持的请求方式,请检查GET/POST
            return BasicResponseObject.fail(BasicResponseStatus.HTTP_METHOD_NOT_ALLOWED);
        }
        if (e instanceof HttpMediaTypeNotSupportedException) {
            // 不支持的参数提交方式,请检查Json/FormEncode
            return BasicResponseObject.fail(BasicResponseStatus.HTTP_UNSUPPORTED_MEDIA_TYPE);
        }

        return BasicResponseObject.fail(BasicResponseStatus.HTTP_INTERNAL_SERVER_ERROR);
    }

}
