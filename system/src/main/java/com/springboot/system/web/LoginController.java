package com.springboot.system.web;

import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.util.AjaxMsgUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:21
 * @param   
 * @return   
 */  
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(LoginController.class);
    private final String RememberMe = "rememberMe";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map loginUserAjax(HttpServletRequest request, String username, String password, HttpSession session) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map model = new HashMap(16);
        String error = "";
        try {
            if(request.getParameter(RememberMe)!=null){
                usernamePasswordToken.setRememberMe(true);
            }
            //完成登录
            subject.login(usernamePasswordToken);
            subject.getSession().setAttribute("__pwd",password);
            Hrmresource hrmresource=(Hrmresource) subject.getPrincipal();
            session.setAttribute("hrmresource", hrmresource);
            model.put("forword", "/");

            //此处将被shiro拦截至successUrl
            return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, model);
        } catch (UnknownAccountException uae) {
            error = msgUtil.getMsg("用户不存在,请申请用户!!!");
        } catch (IncorrectCredentialsException ice) {
            error = msgUtil.getMsg("用户名密码错误,请确认后重新登陆!!!");
//            model.put("username", username);
        } catch (LockedAccountException lae) {
            error = msgUtil.getMsg("账户已被锁定，无法登陆!!!");
        } catch (AuthenticationException ae) {
            error = msgUtil.getMsg("unexpected condition...");
        } catch(Exception e) {
            error = msgUtil.getMsg("登录异常：" ) + e.getMessage();
        }
        LOGGER.info(error);
        model.put("error", error);
        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.ERROR, model);
    }

    @RequestMapping(value = "/loginSuccess")
    @ResponseBody
    public Map loginSuccess() {
        Map model = new HashMap(16);
        model.put("forword", "/");
        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, model);
    }

}