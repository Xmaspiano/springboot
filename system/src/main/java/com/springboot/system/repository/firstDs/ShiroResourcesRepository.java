package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.ShiroResources;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:28
 * @param   
 * @return   
 */  
public interface ShiroResourcesRepository
        extends CrudRepository<ShiroResources, Long>,CommonRepository<ShiroResources> {

    public List<ShiroResources> findByRealNameAndMethod(String realName, String method);

    public List<ShiroResources> findOneByRealNameAndMethodAndShiroAuth(String realName, String method, String shiroAuth);

    public ShiroResources findOneByKeyname(String keyname);

}
