package com.springboot.system.resources.repository.firstDs;

import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.repository.CrudRepository;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:28
 * @param   
 * @return   
 */  
public interface ResourcesRepository
        extends CrudRepository<Resources, Long>,CommonRepository<Resources> {

//    public List<resources> findByRealNameAndMethod(String RealName, String method);

//    public List<resources> findOneByRealNameAndMethodAndShiroAuth(String RealName, String method, String ShiroAuth);

    public Resources findByKeyname(String keyname);

}
