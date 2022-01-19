package com.xiaozu.server.repository;

import com.xiaozu.server.domain.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @author dongpo.li
 * @date 2021/8/11
 */
@Repository
public interface SysUserRepository {

    SysUser selectByUsername(String username);

}
