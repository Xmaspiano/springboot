package com.springboot.system.auth.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:22
 * @param   
 * @return   
 */  
@Controller
@RequestMapping(value = {"/auth/dept"})
public class DeptAuthController {
    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/auth/AuthDept";
    }


}
