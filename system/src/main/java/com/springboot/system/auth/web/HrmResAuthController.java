package com.springboot.system.auth.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.service.HrmresourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/auth/user"})
public class HrmResAuthController {
    @Autowired
    private HrmresourceService hrmresourceService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "system/auth/AuthUser";
    }

    @RequestMapping("/date_grid.json")
    @ResponseBody
    public Map query(@RequestParam( value = "deptid", defaultValue = "0") Long deptid){
        Map jsonMap = new HashMap();
        if(deptid == 0L){
            jsonMap.put("rows",new Hrmresource());
            return jsonMap;
        }

        List<Hrmresource> hrmresList = hrmresourceService.findByDepartmentid(deptid);

        jsonMap.put("rows", CommonUtil.conversionByList( hrmresList ));
        return jsonMap;
    }
}