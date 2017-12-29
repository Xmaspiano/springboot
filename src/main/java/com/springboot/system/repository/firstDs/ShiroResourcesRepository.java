package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.ShiroResources;
import com.springboot.system.repository.CommonRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ShiroResourcesRepository
        extends CrudRepository<ShiroResources, Long>,CommonRepository<ShiroResources> {

    public List<ShiroResources> findByRealNameAndMethod(String RealName, String method);

    public List<ShiroResources> findOneByRealNameAndMethodAndShiroAuth(String RealName, String method, String ShiroAuth);

    public ShiroResources findOneByKeyname(String keyname);

}
