package com.xiaozu.server.domain.vo;

import com.xiaozu.server.domain.SysMenu;
import com.xiaozu.server.utils.BeanCopyHelper;

import java.util.List;

/**
 * @author dongpo.li
 * @date 2021/8/17
 */
public class SysMenuNode {

    private Long id;
    private String menuName;
    private String menuType;
    private String target;
    private String url;
    private String icon;
    private SysMenuNode parent;
    private List<SysMenuNode> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public SysMenuNode getParent() {
        return parent;
    }

    public void setParent(SysMenuNode parent) {
        this.parent = parent;
    }

    public List<SysMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuNode> children) {
        this.children = children;
    }

    public static SysMenuNode from(SysMenu menu) {
        String[] fields = new String[]{
                "menuName", "menuType", "target", "url", "icon"
        };
        BeanCopyHelper<SysMenu> beanCopyHelper = new BeanCopyHelper<>(menu, fields);
        return beanCopyHelper.copy(SysMenuNode.class);
    }
}
