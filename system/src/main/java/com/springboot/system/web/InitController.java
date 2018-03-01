package com.springboot.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:21
 * @param   
 * @return   
 */  
@Controller
public class InitController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init() {
        return "systemLayout/layout_main";
    }
}
