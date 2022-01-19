package com.xiaozu.server.repository;

import com.xiaozu.server.domain.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongpo.li
 * @date 2021/8/11
 */
@Repository
public interface SysMenuRepository {

    List<SysMenu> selectByIds(List<Long> ids);

}
