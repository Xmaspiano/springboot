package com.springboot.system.util;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlbertXmas on 16/8/25.
 */
public class AjaxMsgUtil {
    public static final boolean SUCCESS = true;
    public static final boolean ERROR = false;

    public static Map AjaxMsg(Boolean bo, String msg, Exception ex){
        msg += ex == null?"":ex.getMessage();
        return AjaxMsg(bo, msg);
    }

    public static Map AjaxMsg(Boolean bo, String msg){
        Map jsonMap = new HashMap();
        jsonMap.put("status", bo);
        jsonMap.put("message", msg);
        return jsonMap;
    }

    public static Map AjaxMsg(Boolean bo, Map msg){
        Map jsonMap = new HashMap();
        jsonMap.put("status", bo);
        jsonMap.putAll(msg);
        return jsonMap;
    }
}
