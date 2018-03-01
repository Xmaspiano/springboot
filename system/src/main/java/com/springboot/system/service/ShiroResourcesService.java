package com.springboot.system.service;

import com.springboot.common.service.CommonService;
import com.springboot.system.entity.firstDsE.ShiroResources;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  
public interface ShiroResourcesService extends CommonService<ShiroResources> {

    public List<ShiroResources> findOneBy(ShiroResources shiroResources);

    public List<ShiroResources> findOneBySome(ShiroResources shiroResources);

    public ShiroResources clearRepetitionData(ShiroResources shiroResources);

    public List<ShiroResources> clearSave(List<ShiroResources> list);

    public ShiroResources fingByKeyname(String keyname);
}
