package com.springboot.system.auth.entity;

public enum AuthType {

    user("用户"),
    user_group("用户组"),
    organization_job("组织机构和工作职务"),
    organization_group("组织机构组");

    private final String info;

    AuthType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public static String getKeyString(AuthType type){
        if(type == AuthType.user){
            return "userid";
        }else if(type == AuthType.user_group){
            return "groupid";
        }else if(type == AuthType.organization_group){
            return "deptid";
        }else if(type == AuthType.organization_job){
            return "roleid";
        }
        return "";
    }

    public static AuthType getKeyEnum(String type){
        if("userid".equals(type)){
            return AuthType.user;
        }else if("group".equals(type)){
            return AuthType.user_group;
        }else if("deptid".equals(type)){
            return AuthType.organization_group;
        }else if("roleid".equals(type)){
            return AuthType.organization_job;
        }
        return null;
    }
}
