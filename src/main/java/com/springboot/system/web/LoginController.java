package com.springboot.system.web;

import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.util.AjaxMsgUtil;
import com.springboot.system.util.MsgUtil;
import com.springboot.system.util.MsgUtilNative;
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

@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map loginUserAjax(HttpServletRequest request, String username, String password, HttpSession session) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        Map model = new HashMap();
        String error = "";
        try {
            if(request.getParameter("rememberMe")!=null){
                usernamePasswordToken.setRememberMe(true);
            }
            subject.login(usernamePasswordToken);   //完成登录
            subject.getSession().setAttribute("__pwd",password);
            Hrmresource hrmresource=(Hrmresource) subject.getPrincipal();
            session.setAttribute("hrmresource", hrmresource);
            model.put("forword", "/");
            return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, model);//此处将被shiro拦截至successUrl
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
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.ERROR, model);
    }

    @RequestMapping(value = "/loginSuccess")
    @ResponseBody
    public Map loginSuccess() {
        Map model = new HashMap();
        model.put("forword", "/");
        return AjaxMsgUtil.AjaxMsg(AjaxMsgUtil.SUCCESS, model);
    }

}