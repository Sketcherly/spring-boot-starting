package com.xiaozu.server.configuration.jwt;

import com.xiaozu.server.utils.SpringContextUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
//@Component // 这个不能用直接的方式所以这个舒适不能打开
public class JwtAuthenticationRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationRequestFilter.class);

    private static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HandlerExceptionResolver handlerExceptionResolver =
                SpringContextUtil.getApplicationContext().getBean("handlerExceptionResolver", HandlerExceptionResolver.class);

        String originAuthorizationToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(originAuthorizationToken) || !StringUtils.startsWith(originAuthorizationToken, AUTHORIZATION_TOKEN_PREFIX)) {
            logger.error("originAuthorizationToken is invalidate, {}", originAuthorizationToken);
            AuthenticationException exception = new AuthenticationCredentialsNotFoundException("没有找到鉴权token");
            handlerExceptionResolver.resolveException(request, response, null, exception);
            return;
        }

        String token = StringUtils.substringAfter(originAuthorizationToken, AUTHORIZATION_TOKEN_PREFIX);
        if (StringUtils.isBlank(token)) {
            logger.error("authorization token is empty");
            AuthenticationException exception = new AuthenticationCredentialsNotFoundException("没有找到鉴权token");
            handlerExceptionResolver.resolveException(request, response, null, exception);
            return;
        }

        String username = null;
        try {
            JwtTokenManager jwtTokenManager = SpringContextUtil.getApplicationContext().getBean(JwtTokenManager.class);
            username = jwtTokenManager.getUsernameFromToken(token);
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token has expired", e);
            AuthenticationException exception = new CredentialsExpiredException("JWT Token has expired");
            handlerExceptionResolver.resolveException(request, response, null, exception);
            return;
        } catch (Exception e) {
            logger.error("get username from token exception", e);
            AuthenticationException exception = new AuthenticationServiceException("get username from token exception");
            handlerExceptionResolver.resolveException(request, response, null, exception);
            return;
        }

        if (StringUtils.isBlank(username)) {
            logger.error("get username=empty from token");
            AuthenticationException exception = new AuthenticationServiceException("get username=empty from token");
            handlerExceptionResolver.resolveException(request, response, null, exception);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, List.of());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }
}
