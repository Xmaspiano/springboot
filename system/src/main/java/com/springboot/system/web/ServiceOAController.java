package com.springboot.system.web;

import com.springboot.system.oa.entity.secondDsE.Hrmresource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:21
 * @param   
 * @return   
 */  
@Controller
public class ServiceOAController {

    @RequestMapping("/oalink")
    public String index(@RequestParam("url")String url) {
        SecurityUtils.getSubject().getPrincipal();
        Hrmresource hrmresource = (Hrmresource)SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject();
        String pwd = subject.getSession().getAttribute("__pwd").toString();
        return "redirect:http://88.88.88.159/login/LinkOA.jsp?loginid="+hrmresource.getLoginid()+"&paswd="+pwd+"&url="+url;
    }
}
