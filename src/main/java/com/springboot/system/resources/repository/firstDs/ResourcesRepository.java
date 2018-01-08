package com.springboot.system.resources.repository.firstDs;

import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ResourcesRepository
        extends CrudRepository<Resources, Long>,CommonRepository<Resources> {

//    public List<resources> findByRealNameAndMethod(String RealName, String method);

//    public List<resources> findOneByRealNameAndMethodAndShiroAuth(String RealName, String method, String ShiroAuth);

    public Resources findByKeyname(String keyname);

}
