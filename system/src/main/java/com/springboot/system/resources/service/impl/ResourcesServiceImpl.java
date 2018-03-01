package com.springboot.system.resources.service.impl;

import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.entity.firstDsE.ShiroResources;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.resources.repository.firstDs.ResourcesRepository;
import com.springboot.system.resources.service.ResourcesService;
import com.springboot.system.service.ShiroResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:27
 * @param   
 * @return   
 */  
@Service
@Transactional
public class ResourcesServiceImpl
        extends BaseCommonServiceImpl<Resources, ResourcesRepository>
        implements ResourcesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesService.class);

    @Autowired
    ShiroResourcesService shiroResourcesService;

    @Override
    public List<Resources> synchronous(Long[] id, String[] keyName) {
//        resources resources = null;
//        ShiroResources shiroResources = null;

//        for(int i = 0; i<id.length || i<KeyName.length; i++){
//            resources = getRepository().findOne(id[i]);
//            shiroResources = shiroResourcesService.fingByKeyname(KeyName[i]);
//        }
        //目前先平移
        return synchronousBoth();
    }

    @Override
    public Resources findByKeyname(String keyname) {
        return this.getRepository().findByKeyname(keyname);
    }

    private List<Resources> synchronousBoth(){
        Resources resources = null;
        List<Resources> liResources = new ArrayList();
        getRepository().deleteAll();
        for(ShiroResources shiroResources:shiroResourcesService.findAll()){
            resources = new Resources();
            resources.setKeyname(shiroResources.getKeyname());
            resources.setName(shiroResources.getName());
            resources.setAvailable(shiroResources.isAvailable());
            resources.setMethod(shiroResources.getMethod());
            resources.setShiroAuth(shiroResources.getShiroAuth());
            resources.setRealName(shiroResources.getRealName());
            liResources.add(resources);
        }
        return getRepository().save(liResources);
    }
}
