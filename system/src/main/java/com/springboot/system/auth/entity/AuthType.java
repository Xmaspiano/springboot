package com.springboot.system.auth.entity;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:23
 * @param   
 * @return   
 */  
public enum AuthType {
    /* 用户 */
    user("用户"),
    /* 用户组 */
    user_group("用户组"),
    /* 组织机构和工作职务 */
    organization_job("组织机构和工作职务"),
    /* 组织机构组 */
    organization_group("组织机构组");

    private static final String USER_ID = "USER_ID";
    private static final String GROUP_ID = "GROUP_ID";
    private static final String DEPT_ID = "DEPT_ID";
    private static final String ROLE_ID = "ROLE_ID";
    private final String info;

    AuthType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public static String getKeyString(AuthType type){
        if(type == AuthType.user){
            return USER_ID;
        }else if(type == AuthType.user_group){
            return GROUP_ID;
        }else if(type == AuthType.organization_group){
            return DEPT_ID;
        }else if(type == AuthType.organization_job){
            return ROLE_ID;
        }
        return "";
    }

    public static AuthType getKeyEnum(String type){
        if(USER_ID.equals(type)){
            return AuthType.user;
        }else if(GROUP_ID.equals(type)){
            return AuthType.user_group;
        }else if(DEPT_ID.equals(type)){
            return AuthType.organization_group;
        }else if(ROLE_ID.equals(type)){
            return AuthType.organization_job;
        }
        return null;
    }
}
