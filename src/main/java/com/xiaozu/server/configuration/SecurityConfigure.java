package com.xiaozu.server.configuration;

import com.xiaozu.server.configuration.jwt.JwtAuthenticationRequestFilter;
import com.xiaozu.server.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Spring Security 配置
 */
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不需要鉴权的路径
        String[] ignoreAuthUrls = new String[]{
                Constant.SERVER_ADMIN_NAMESPACE + "/login",
        };
        web.ignoring().antMatchers(ignoreAuthUrls);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher(Constant.SERVER_ADMIN_NAMESPACE + "/**")
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // admin下的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().addFilterBefore(new JwtAuthenticationRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                ;

        // 禁用缓存
        http.headers().cacheControl().disable();
        http.csrf().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
