package com.xiaozu.server.configuration.jwt;

import com.xiaozu.server.domain.SysUser;
import com.xiaozu.server.service.SysUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dongpo.li
 * @date 2022/1/14
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUserQuery = new SysUser();
        sysUserQuery.setUsername(username);

        SysUser sysUser = sysUserService.selectByUsername(sysUserQuery);
        if (sysUser == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(sysUser.getUsername(), sysUser.getPassword(), List.of());
    }

}
