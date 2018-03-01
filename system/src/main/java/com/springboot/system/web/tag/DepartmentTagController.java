package com.springboot.system.web.tag;

import com.springboot.system.entity.secondDsE.Hrmdepartment;
import com.springboot.system.service.HrmdepartmentService;
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
@RequestMapping(value = {"/dept/tag"})
public class DepartmentTagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentTagController.class);

    @Autowired
    private HrmdepartmentService departmentService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/home/left";
    }

    /**
     * 根据ID查询所有下级部门
     * @param id
     * @return
     */
    @RequestMapping(value = "/dept_tree.json" )//获取部门树
    @ResponseBody
    public Map getTreeData(@RequestParam(defaultValue = "1",value = "parentid") Long id){
        List<Hrmdepartment> departmentList = null;
        //亚心根目录,其他集团暂不开放
        if(id == 1L) {
            departmentList = departmentService.findByYXDept();
        }else{
            //二级菜单
            departmentService.findAllBySuper(getLevelTwodept(id).getId());
        }
        Map jsonMap = new HashMap();
        jsonMap.put("total",departmentList.size());
        jsonMap.put("rows",this.test(departmentList));
        return jsonMap;
    }

    @RequestMapping(value = "/dept_tree_url.json" )
    @ResponseBody
    public Map getTreeDataUrl(@RequestParam(defaultValue = "1",value = "id") Long id){
        Map jsonMap = new HashMap();
        jsonMap.put("success", true);

        Hrmdepartment department = departmentService.findOne(id);
        Hrmdepartment osPartdept = getLevelTwodept(department.getSupdepid());

        if(id == 0L){//默认
            jsonMap.put("title", "首页");
            jsonMap.put("id", 0);
            jsonMap.put("url", "/index");
            jsonMap.put("parentName", "菜单");
            jsonMap.put("parentId", 1);
        }else{
            jsonMap.put("title", department.getDepartmentname());
            jsonMap.put("id", department.getId());
            jsonMap.put("url", "department.getRemark()");
            jsonMap.put("parentName", osPartdept.getDepartmentname());
            jsonMap.put("parentId", department.getSupdepid());
        }
        return jsonMap;
    }

    private Hrmdepartment getLevelTwodept(Long id){
        Hrmdepartment osPartdept;
        if(id > 1L) {
            osPartdept = departmentService.findOne(id);
            if (osPartdept.getSupdepid() != 1L) {
                return getLevelTwodept(osPartdept.getSupdepid());
            }
        }else {
            osPartdept = new Hrmdepartment();
            osPartdept.setId(id);
        }
        return osPartdept;
    }

    public Map[] test(List<Hrmdepartment> departmentList){
        Map[] jsonMap = new HashMap[departmentList.size()];
        Hrmdepartment department;
        for (int i = 0; i < jsonMap.length; i++) {
            department = departmentList.get(i);
            jsonMap[i] = new HashMap();

            jsonMap[i].put("id",department.getId());
            jsonMap[i].put("text",department.getDepartmentname());
            if(department.getHrmDepartmentList().size() > 0){
                jsonMap[i].put("children",this.test(department.getHrmDepartmentList()));
            }
        }
        return jsonMap;
    }
}
