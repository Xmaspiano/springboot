package com.springboot.system.service.impl;

import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.entity.firstDsE.ShiroResources;
import com.springboot.system.repository.firstDs.ShiroResourcesRepository;
import com.springboot.system.service.ShiroResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  
@Service
@Transactional
public class ShiroResourcesServiceImpl
        extends BaseCommonServiceImpl<ShiroResources, ShiroResourcesRepository>
        implements ShiroResourcesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroResourcesService.class);

    @Override
    public List<ShiroResources> findOneBy(ShiroResources shiroResources){
        return getRepository().findByRealNameAndMethod(shiroResources.getRealName(), shiroResources.getMethod());
    }

    @Override
    public List<ShiroResources> findOneBySome(ShiroResources shiroResources){
        return getRepository().findOneByRealNameAndMethodAndShiroAuth(
                shiroResources.getRealName(),
                shiroResources.getMethod(),
                shiroResources.getShiroAuth()
        );
    }

    @Override
    public ShiroResources clearRepetitionData(ShiroResources shiroResources){
        List<ShiroResources> list = findOneBySome(shiroResources);
        for (int i = 1; i<list.size(); i++){
            getRepository().delete(list.get(i));
        }
        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<ShiroResources> clearSave(List<ShiroResources> list) {
        this.deleteAll();
        return (List<ShiroResources>)save(list);
    }

    @Override
    public ShiroResources fingByKeyname(String keyname) {
        return getRepository().findOneByKeyname(keyname);
    }

    public void deleteAll(){
        getRepository().deleteAll();
    }

}
