package com.springboot.system.resources.service;

import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.common.service.CommonService;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ResourcesService extends CommonService<Resources> {

    public List<Resources> synchronous(Long[] id,String[] KeyName);

    public Resources findByKeyname(String keyname);
}
