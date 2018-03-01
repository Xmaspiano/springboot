package com.springboot.system.menu.service;

import com.springboot.common.service.CommonService;
import com.springboot.system.menu.entity.firstDsE.OsMenu;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
public interface OsMenuService extends CommonService<OsMenu> {

    public List<OsMenu> findAllBySuper(Long id);

    public List<OsMenu> findAllRealLife();

}