package com.xiaozu.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author dongpo.li
 * @date 2021/8/11
 */
public class SysUser extends BaseEntity {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String roles; // 英文逗号分隔的角色id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}
