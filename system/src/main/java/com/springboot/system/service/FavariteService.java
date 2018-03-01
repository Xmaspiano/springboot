package com.springboot.system.service;


import com.springboot.common.service.CommonService;
import com.springboot.system.entity.firstDsE.Favarite;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:25
 * @param   
 * @return   
 */  
public interface FavariteService extends CommonService<Favarite> {

    public Favarite findByUserIdAndMenuId(Long userId, Long menuId);

    public void deleteByUserIdAndMenuId(Long userId, Long menuId);

    public List<Favarite> findByUserId(Long userId);
}