package com.xiaozu.server.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.xiaozu.server.constant.Constant;
import com.xiaozu.server.domain.SysMenu;
import com.xiaozu.server.domain.SysRole;
import com.xiaozu.server.domain.SysUser;
import com.xiaozu.server.domain.vo.SysMenuNode;
import com.xiaozu.server.repository.SysMenuRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author dongpo.li
 * @date 2021/8/12
 */
@Service
public class SysMenuService {

    // 用户权限缓存
    private static final Cache<Long, List<String>> USER_AUTHORITY_CACHE = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .maximumSize(10_000)
            .build();

    @Resource
    private SysMenuRepository sysMenuRepository;
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 查询用户权限列表(用户所有角色对应的所有路径)
     *
     * @param user
     * @return
     */
    public List<String> selectUserAuthorityList(SysUser user) {

        if (user == null) {
            return List.of();
        }

        return USER_AUTHORITY_CACHE.get(user.getId(), id -> buildUserAuthorityList(user));
    }

    private List<String> buildUserAuthorityList(SysUser user) {
        // 1. 查询用户角色
        List<SysRole> sysRoleList = sysRoleService.selectUserRole(user);
        if (CollectionUtils.isEmpty(sysRoleList)) {
            return List.of();
        }

        Set<Long> menuIdList = new HashSet<>(); // 去重用
        for (SysRole role : sysRoleList) {
            String menuIds = role.getMenuIds();
            if (StringUtils.isBlank(menuIds)) {
                continue;
            }

            Constant.COMMA_SPLITTER.splitToList(menuIds).forEach(id -> {
                menuIdList.add(NumberUtils.toLong(id));
            });

        }

        List<String> grantedAuthorityPath = new ArrayList<>();
        List<SysMenu> sysMenuList = sysMenuRepository.selectByIds(new ArrayList<>(menuIdList));
        for (SysMenu menu : sysMenuList) {
            if (StringUtils.equals(menu.getMenuType(), SysMenu.MENU_TYPE_MENU)
                    || StringUtils.equals(menu.getMenuType(), SysMenu.MENU_TYPE_BTN)) {
                grantedAuthorityPath.add(menu.getUrl());
            }
        }

        return grantedAuthorityPath;
    }

    private List<SysMenuNode> buildMenuTree(List<SysMenu> sysMenuList) {
        // 缓存所有菜单节点,可以加快菜单树的构建,以空间换时间
        Map<Long, SysMenuNode> menuNodeCache = new HashMap<>(sysMenuList.size());
        for (SysMenu menu : sysMenuList) {
            menuNodeCache.put(menu.getId(), SysMenuNode.from(menu));
        }

        // 根节点,最终输出的结果
        List<SysMenuNode> rootList = new ArrayList<>();

        for (SysMenu menu : sysMenuList) {
            if (menu.getParentId() == null || menu.getParentId() == 0) { // 其中的一个根节点
                SysMenuNode root = menuNodeCache.get(menu.getId());
                rootList.add(root);
                continue;
            }

            SysMenuNode child = menuNodeCache.get(menu.getId());

            SysMenuNode parent = menuNodeCache.get(menu.getParentId());
            if (parent != null) {
                List<SysMenuNode> children = parent.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(child);
            }

        }

        return rootList;
    }

}
