package com.springboot.architecture.filter;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:17
 * @param   
 * @return   
 */  
public class TokenOverFilter extends AccessControlFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenOverFilter.class);
    private final String XMLHttpRequest = "XMLHttpRequest";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);

        if(subject.isAuthenticated() || subject.isRemembered() || isLoginRequest(servletRequest, servletResponse)) {
            return Boolean.TRUE;
        }

        // ajax请求
        if (isAjax(servletRequest)) {
            Map<String, String> resultMap = new HashMap<String, String>(16);
            LOGGER.debug("当前用户没有登录，并且是Ajax请求！");
            resultMap.put("login_status", "999");
            // 当前用户没有登录！
            resultMap.put("message","\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");

            ((HttpServletResponse)servletResponse).setStatus(999);
            out(servletResponse, resultMap);
        }
        return Boolean.FALSE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        return Boolean.FALSE;
    }

    private boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if(XMLHttpRequest.equalsIgnoreCase(header)){
            LOGGER.debug("当前请求为Ajax请求");
            return Boolean.TRUE;
        }
        LOGGER.debug("当前请求非Ajax请求");
        return Boolean.FALSE;
    }

    private void out(ServletResponse response, Map<String, String> resultMap){
        PrintWriter out = null;
        try {
            //设置编码
            response.setCharacterEncoding("UTF-8");
            //设置返回类型
            response.setContentType("application/json");
            out = response.getWriter();
            //输出
            out.println(JSONObject.toJSON(resultMap));
        } catch (Exception e) {
            LOGGER.error("输出JSON报错。",e);
        }finally{
            if(null != out){
                out.flush();
                out.close();
            }
        }
    }
}
