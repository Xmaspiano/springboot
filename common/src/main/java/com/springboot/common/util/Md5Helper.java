package com.springboot.common.util;

import java.security.MessageDigest;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:14
 * @param   
 * @return   
 */  
public class Md5Helper {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    public static String getMD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("UTF-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
