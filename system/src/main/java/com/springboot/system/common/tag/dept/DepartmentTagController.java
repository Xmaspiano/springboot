package com.springboot.system.common.tag.dept;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.common.tag.BaseTagController;
import com.springboot.system.oa.entity.secondDsE.Hrmdepartment;
import com.springboot.system.oa.service.HrmdepartmentService;
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

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:22
 * @param
 * @return
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping(value = {"/common/tag/dept/"})
public class DepartmentTagController extends BaseTagController {
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
        Map[] departmentList = null;

        if(id == 1L) {
            departmentList = departmentService.findByYXDept();
        }else{
            //二级菜单
            departmentService.findAllBySuper(getLevelTwodept(id).getId());
        }
        Map jsonMap = new HashMap(16);
        jsonMap.put("total",departmentList.length);
        jsonMap.put("rows",departmentList);
        return jsonMap;
    }

    @RequestMapping(value = "/dept_tree_url.json" )
    @ResponseBody
    public Map getTreeDataUrl(@RequestParam(defaultValue = "1",value = "id") Long id){
        Map jsonMap = new HashMap(16);
        jsonMap.put("success", true);

        Hrmdepartment department = departmentService.findOne(id);
        Hrmdepartment osPartdept = getLevelTwodept(department.getSupdepid());

        //默认
        if(id == 0L){
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

    @RequestMapping("/getSubCompany.json")
    @ResponseBody
    public List<Hrmdepartment> getSubCompany(){
        return departmentService.findSubCompany();
    }
}
