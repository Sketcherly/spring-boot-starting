package com.xiaozu.server.domain;

/**
 * @author dongpo.li
 * @date 2021/8/11
 */
public class SysRole extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限
     */
    private String roleKey;
    /**
     * 角色排序
     */
    private String roleSort;
    /**
     * 菜单权限
     */
    private String menuIds;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(String roleSort) {
        this.roleSort = roleSort;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
