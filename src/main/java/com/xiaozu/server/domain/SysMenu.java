package com.xiaozu.server.domain;

/**
 * @author dongpo.li
 * @date 2021/8/11
 */
public class SysMenu extends BaseEntity {

    public static final String MENU_TYPE_DIR = "DIR"; // 目录
    public static final String MENU_TYPE_MENU = "MENU"; // 菜单
    public static final String MENU_TYPE_BTN = "BTN"; // 按钮(功能)

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private String orderNum;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 打开方式：menuItem页签 menuBlank新窗口
     */
    private String target;

    /**
     * 类型:0目录,1菜单,2按钮
     */
    private String menuType;

    /**
     * 菜单状态:0显示,1隐藏
     */
    private String visible;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
