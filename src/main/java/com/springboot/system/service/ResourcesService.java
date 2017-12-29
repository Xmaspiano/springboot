package com.springboot.system.service;

import com.springboot.system.entity.firstDsE.Resources;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ResourcesService extends CommonService<Resources>{

    public List<Resources> synchronous(long[] id,String[] KeyName);
}
