package com.springboot.system.resources.service;

import com.springboot.system.resources.entity.firstDsE.Resources;
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
public interface ResourcesService extends CommonService<Resources> {

    public List<Resources> synchronous(Long[] id,String[] keyName);

    public Resources findByKeyname(String keyname);
}
