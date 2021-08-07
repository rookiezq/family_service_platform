package com.rookied.returnJson;

/**
 * 功能模块
 * @author zhangqiang
 * @date 2021/8/7
 */
public class Permission {
    /**
     * 功能模块id
     */
    private String permissionId;

    public Permission() {
    }

    public Permission(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public Permission setPermissionId(String permissionId) {
        this.permissionId = permissionId;
        return this;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId='" + permissionId + '\'' +
                '}';
    }
}
