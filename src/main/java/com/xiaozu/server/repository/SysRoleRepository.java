package com.xiaozu.server.repository;

import com.xiaozu.server.domain.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongpo.li
 * @date 2021/8/11
 */
@Repository
public interface SysRoleRepository {

    List<SysRole> selectByIds(List<String> ids);

}
