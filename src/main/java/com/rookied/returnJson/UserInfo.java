package com.rookied.returnJson;

/**
 * 用户权限信息
 * @author zhangqiang
 * @date 2021/8/7
 */
public class UserInfo {
    /**
     * 用户名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar = "/avatar2.jpg";

    /**
     * 角色
     */
    private Permissions role;

    public UserInfo() {
    }

    public UserInfo(String name, Permissions role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public UserInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserInfo setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Permissions getRole() {
        return role;
    }

    public UserInfo setRole(Permissions role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role=" + role +
                '}';
    }
}
