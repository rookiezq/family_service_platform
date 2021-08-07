package com.rookied.returnJson;

import java.util.List;

/**
 * 角色对应的所有功能模块
 * @author zhangqiang
 * @date 2021/8/7
 */
public class Permissions {
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public Permissions setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
        return this;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "permissions=" + permissions +
                '}';
    }
}
