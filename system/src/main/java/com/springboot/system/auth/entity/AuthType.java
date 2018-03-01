package com.springboot.system.auth.entity;

public enum AuthType {
    /* 用户 */
    user("用户"),
    /* 用户组 */
    user_group("用户组"),
    /* 组织机构和工作职务 */
    organization_job("组织机构和工作职务"),
    /* 组织机构组 */
    organization_group("组织机构组");

    private static final String UserId = "UserId";
    private static final String GroupId = "GroupId";
    private static final String DeptId = "DeptId";
    private static final String RoleId = "RoleId";
    private final String info;

    AuthType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public static String getKeyString(AuthType type){
        if(type == AuthType.user){
            return UserId;
        }else if(type == AuthType.user_group){
            return GroupId;
        }else if(type == AuthType.organization_group){
            return DeptId;
        }else if(type == AuthType.organization_job){
            return RoleId;
        }
        return "";
    }

    public static AuthType getKeyEnum(String type){
        if(UserId.equals(type)){
            return AuthType.user;
        }else if(GroupId.equals(type)){
            return AuthType.user_group;
        }else if(DeptId.equals(type)){
            return AuthType.organization_group;
        }else if(RoleId.equals(type)){
            return AuthType.organization_job;
        }
        return null;
    }
}
