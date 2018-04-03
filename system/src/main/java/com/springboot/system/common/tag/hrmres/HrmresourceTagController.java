package com.springboot.system.common.tag.hrmres;

import com.springboot.system.common.tag.BaseTagController;
import com.springboot.system.oa.entity.secondDsE.Hrmresource;
import com.springboot.system.oa.model.HrmresSafePage;
import com.springboot.system.oa.service.HrmresourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = {"/common/tag/hrmres/"})
public class HrmresourceTagController extends BaseTagController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HrmresourceTagController.class);

    @Autowired
    private HrmresSafePage hrmresSafePage;

    @Autowired
    HrmresourceService hrmresourceService;

    @RequestMapping("findByUserId.json")
    @ResponseBody
    public HrmresSafePage findByUserId(@RequestParam("userId") Long userId){
        if(userId != null || "".equals(userId)) {
            return hrmresSafePage.changeByEntity(hrmresourceService.findOne(userId));
        }

        return new HrmresSafePage();
    }
}
