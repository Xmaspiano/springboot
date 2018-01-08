package com.springboot.system.service;



import com.springboot.common.service.CommonService;
import com.springboot.system.entity.secondDsE.Hrmresource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

public interface HrmresourceService extends CommonService<Hrmresource>
{
    public Hrmresource findByLoginid(String loginid);

    public List<Hrmresource> findByDDC();

    //====
    public Hrmresource findOne(Long id);

    public List<Hrmresource> findAll();

//    public Page<Hrmresource> findAll(Pageable pageable);

    public Page<Hrmresource> findAll(Specification<Hrmresource> spec, Pageable pageable);

    public Hrmresource save(Hrmresource Hrmresource);

    public Iterable<Hrmresource> save(Iterable<Hrmresource> entities);

    public void delete(Long id);
}
