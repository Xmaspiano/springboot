package com.springboot.system.web;

import com.springboot.common.util.CommonUtil;
import com.springboot.system.entity.firstDsE.Favarite;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.menu.service.OsMenuService;
import com.springboot.system.service.FavariteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = {"/favarite"})
public class FavariteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FavariteController.class);

    @Autowired
    private FavariteService favariteService;
    @Autowired
    private OsMenuService osMenuService;

    @RequestMapping(value = "/check" )
    @ResponseBody
    public Map onFavarote(
            @RequestParam(defaultValue = "",value = "title") String title,
            @RequestParam(defaultValue = "false",value = "flag") boolean flag,
            @RequestParam(defaultValue = "false",value = "menuid") Long menuId
    ){
        Map jsonMap = new HashMap(16);
        Favarite favarite = new Favarite();
        try {

            Subject subject = SecurityUtils.getSubject();
            Hrmresource hrmresource=(Hrmresource) subject.getPrincipal();

            if(flag) {
                favarite.setUserId(hrmresource.getId());
                favarite.setMenuId(menuId);
                favarite.setSort("A");
                favariteService.save(favarite);
            }else{

                favariteService.deleteByUserIdAndMenuId(hrmresource.getId(), menuId);
            }

            jsonMap.put("success", true);
            jsonMap.put("message", "保存成功...");
        }catch(Exception e){
            e.printStackTrace();
            jsonMap.put("success", false);
            jsonMap.put("message", "保存失败..."+e.getMessage());
        }
        return jsonMap;

    }

    @RequestMapping(value = "/menu_favarite_url.json" )
    @ResponseBody
    public Map getFavariteDataUrl(){
        Map jsonMap = new HashMap(16);
        jsonMap.put("success", true);
        Subject subject = SecurityUtils.getSubject();
        Hrmresource hrmresource=(Hrmresource) subject.getPrincipal();
        List<Favarite> favarites = favariteService.findByUserId(hrmresource.getId());

        jsonMap.put("rows", CommonUtil.conversionByList(favarites));
        return jsonMap;
    }
}