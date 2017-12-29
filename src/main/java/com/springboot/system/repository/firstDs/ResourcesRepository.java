package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.Resources;
import com.springboot.system.repository.CommonRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by AlbertXmas on 17/8/29.
 */
public interface ResourcesRepository
        extends CrudRepository<Resources, Long>,CommonRepository<Resources> {

//    public List<Resources> findByRealNameAndMethod(String RealName, String method);

//    public List<Resources> findOneByRealNameAndMethodAndShiroAuth(String RealName, String method, String ShiroAuth);

}
