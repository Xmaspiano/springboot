package com.springboot.system.oa.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.model.DepartmentTree;
import com.springboot.system.oa.entity.secondDsE.Hrmdepartment;
import com.springboot.system.oa.service.HrmdepartmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping(value = {"/hrmdepartment"})
public class HrmDepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmDepartmentController.class);

    @Autowired
    private DepartmentTree departmentTree;

    @Autowired
    private HrmdepartmentService hrmdepartmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/oa/dept/dept";
    }


    @RequestMapping(value = "/date_treegrid.json" )
    @ResponseBody
    @RequiresPermissions("hrmdepartment:view")
    public Map getTreeGridData(){
        Map jsonMap = new HashMap(16);
        List<Hrmdepartment> hrmdepartmentList = hrmdepartmentService.findByCommonDept();
        jsonMap.put("rows", CommonUtil.conversionByList(departmentTree.changeByEntitys(hrmdepartmentList)));
        return jsonMap;
    }
}