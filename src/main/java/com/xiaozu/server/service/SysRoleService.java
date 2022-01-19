package com.xiaozu.server.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.xiaozu.server.constant.Constant;
import com.xiaozu.server.domain.SysRole;
import com.xiaozu.server.domain.SysUser;
import com.xiaozu.server.repository.SysRoleRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dongpo.li
 * @date 2021/8/12
 */
@Service
public class SysRoleService {

    private static final Cache<Long, List<SysRole>> USER_ROLE_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(10_000)
            .build();

    @Resource
    private SysRoleRepository sysRoleRepository;

    public List<SysRole> selectUserRole(SysUser user) {
        if (user == null || StringUtils.isBlank(user.getRoles())) {
            return List.of();
        }

        return USER_ROLE_CACHE.get(user.getId(), id -> {
            List<String> ids = Constant.COMMA_SPLITTER.splitToList(user.getRoles());
            if (CollectionUtils.isEmpty(ids)) {
                return List.of();
            }
            return sysRoleRepository.selectByIds(ids);
        });

    }

}
