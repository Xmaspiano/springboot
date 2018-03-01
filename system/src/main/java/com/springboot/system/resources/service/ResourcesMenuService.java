package com.springboot.system.resources.service;

import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
import com.springboot.common.service.CommonService;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:27
 * @param   
 * @return   
 */  
public interface ResourcesMenuService extends CommonService<ResourcesMenu> {

    public List<ResourcesMenu> findByMenuid(Long menuid);

    public void deleteByMenuidAndKeyname(Long menuid, String keyname);
}
