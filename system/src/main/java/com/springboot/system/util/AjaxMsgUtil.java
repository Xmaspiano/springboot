package com.springboot.system.util;


import java.util.HashMap;
import java.util.Map;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
public class AjaxMsgUtil {
    public static final boolean SUCCESS = true;
    public static final boolean ERROR = false;

    public static Map ajaxMsg(Boolean bo, String msg, Exception ex){
        msg += ex == null?"":ex.getMessage();
        return ajaxMsg(bo, msg);
    }

    public static Map ajaxMsg(Boolean bo, String msg){
        Map jsonMap = new HashMap(16);
        jsonMap.put("status", bo);
        jsonMap.put("message", msg);
        return jsonMap;
    }

    public static Map ajaxMsg(Boolean bo, Map msg){
        Map jsonMap = new HashMap(16);
        jsonMap.put("status", bo);
        jsonMap.putAll(msg);
        return jsonMap;
    }
}
