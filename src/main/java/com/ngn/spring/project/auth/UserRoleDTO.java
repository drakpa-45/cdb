package com.ngn.spring.project.auth;

/**
 * ==================================================================================
 * Created by user on 3/8/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public class UserRoleDTO {
    private String userId;
    private String roleId;
    private String roleName;
    private Integer roleRefNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleRefNo() {
        return roleRefNo;
    }

    public void setRoleRefNo(Integer roleRefNo) {
        this.roleRefNo = roleRefNo;
    }
}
