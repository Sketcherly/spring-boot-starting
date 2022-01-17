package com.xiaozu.server.configuration.jwt;

import com.xiaozu.server.domain.vo.BasicResponseObject;
import com.xiaozu.server.utils.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
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
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
            throws IOException, ServletException {

        BasicResponseObject<?> resp = JwtAuthenticationException.NON_AUTH_RESPONSE;

        response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        converter.write(JsonUtils.toJson(resp), MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
    }

}
