package com.example.salebook.model;

public class Role {
    private int roleId;
    private String roleName;

    public Role() {
    }

    public Role(String name){
        this.setRoleName(name);
    }

    public String getDisplayText() {
        return roleId + " - " + roleName; // Ví dụ: "1: Category A"
    }

    @Override
    public String toString() {
        return getDisplayText();
    }
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
