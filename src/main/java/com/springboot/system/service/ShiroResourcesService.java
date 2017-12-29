package com.springboot.system.service;

import com.springboot.system.entity.firstDsE.ShiroResources;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ShiroResourcesService extends CommonService<ShiroResources>{

    public List<ShiroResources> findOneBy(ShiroResources shiroResources);

    public List<ShiroResources> findOneBySome(ShiroResources shiroResources);

    public ShiroResources clearRepetitionData(ShiroResources shiroResources);

    public List<ShiroResources> clearSave(List<ShiroResources> list);

    public ShiroResources fingByKeyname(String keyname);
}
