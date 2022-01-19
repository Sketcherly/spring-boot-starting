package com.xiaozu.server.service;

import com.xiaozu.server.domain.SysUser;
import com.xiaozu.server.repository.SysUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dongpo.li
 * @date 2021/8/12
 */
@Service
public class SysUserService {

    @Resource
    private SysUserRepository sysUserRepository;

    public SysUser selectByUsername(SysUser user) {
        return sysUserRepository.selectByUsername(user.getUsername());
    }

}
