package com.springboot.common.config.interceptors;

import com.springboot.system.util.MsgUtil;
import com.springboot.system.util.MsgUtilNative;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthInterceptor implements HandlerInterceptor {
//    @Autowired
    private final MsgUtil msgUtil;
    private List<String> excludeUrls;// 不需要拦截的资源

    public AuthInterceptor(){
        msgUtil = new MsgUtilNative(AuthInterceptor.class);
    }

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }
    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        msgUtil.getMsg(">>>MyInterceptor2>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        msgUtil.getMsg(">>>MyInterceptor2>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        msgUtil.getMsg(">>>MyInterceptor2>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }



    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
     * SpringMVC中的Interceptor拦截器是链式的，可以同时存在多个Interceptor，
     * 然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在Controller方法调用之前调用。
     * SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//        HttpSession session = request.getSession();
//        String requestURI = request.getRequestURI();
//        String contextPath = request.getContextPath();
//        String url = requestURI.substring(contextPath.length() + 1);
//
//        // 测试用
//        if (true) {
//            return true;
//        }
//
//        // 如果要访问的资源是不需要验证的
////        if (url.indexOf("wechatController") > -1 || excludeUrls.contains(url)) {
////            return true;
////        }
//
//        // 获取用户 信息 验证 然后判断  TODO
//
//
//
//
//        return false;
//    }

}