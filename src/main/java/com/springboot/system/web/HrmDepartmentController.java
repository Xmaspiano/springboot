package com.springboot.system.web;


import com.springboot.system.entity.firstDsE.OsMenu;
import com.springboot.system.entity.secondDsE.Hrmdepartment;
import com.springboot.system.service.HrmdepartmentService;
import com.springboot.system.service.OsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/hrmdepartment"})
public class HrmDepartmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmDepartmentController.class);

    @Autowired
    private HrmdepartmentService hrmdepartmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "systemLayout/dept/dept";
    }

    @RequestMapping(value = "/date_treegrid.json" )
    @ResponseBody
    public Map getTreeGridData(){
        Map jsonMap = new HashMap();
        List<Hrmdepartment> mList = hrmdepartmentService.findByYXDept();

        Map[] treeMap = new HashMap[mList.size()];
        Hrmdepartment sonVO;
        jsonMap.put("total",treeMap.length);
        for (int i = 0; i < treeMap.length; i++) {
            treeMap[i] = new HashMap();
            sonVO = mList.get(i);
            treeMap[i].put("id",sonVO.getId());
            treeMap[i].put("departmentname",sonVO.getDepartmentname());
            treeMap[i].put("canceled",sonVO.getCanceled());
            treeMap[i].put("supdepid",sonVO.getSupdepid());
            treeMap[i].put("subcompanyid1",sonVO.getSubcompanyid1());
            if(sonVO.getSupdepid() != 1l) {
                treeMap[i].put("_parentId", sonVO.getSupdepid());
            }
        }
        jsonMap.put("rows",treeMap);
        return jsonMap;
    }
}