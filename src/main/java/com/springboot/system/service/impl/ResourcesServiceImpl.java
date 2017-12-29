package com.springboot.system.service.impl;

import com.springboot.system.entity.firstDsE.Resources;
import com.springboot.system.entity.firstDsE.ShiroResources;
import com.springboot.system.repository.firstDs.ResourcesRepository;
import com.springboot.system.service.ResourcesService;
import com.springboot.system.service.ShiroResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
@Service
@Transactional
public class ResourcesServiceImpl
        extends CommonServiceImpl<Resources, ResourcesRepository>
        implements ResourcesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesService.class);

    @Autowired
    ShiroResourcesService shiroResourcesService;

    @Override
    public List<Resources> synchronous(long[] id, String[] KeyName) {
//        Resources resources = null;
//        ShiroResources shiroResources = null;

//        for(int i = 0; i<id.length || i<KeyName.length; i++){
//            resources = getRepository().findOne(id[i]);
//            shiroResources = shiroResourcesService.fingByKeyname(KeyName[i]);
//        }
        return synchronousBoth();//目前先平移
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