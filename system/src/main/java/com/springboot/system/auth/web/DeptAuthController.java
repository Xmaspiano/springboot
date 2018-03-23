package com.springboot.system.auth.web;


import com.springboot.system.oa.service.HrmdepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HrmdepartmentService departmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/auth/AuthDept";
    }

}
