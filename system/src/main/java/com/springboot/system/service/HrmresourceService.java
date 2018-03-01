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

    @Override
    public Hrmresource findOne(Long id);

    @Override
    public List<Hrmresource> findAll();
//  @Override
//    public Page<Hrmresource> findAll(Pageable pageable);

    @Override
    public Page<Hrmresource> findAll(Specification<Hrmresource> spec, Pageable pageable);

    @Override
    public Hrmresource save(Hrmresource Hrmresource);

    @Override
    public Iterable<Hrmresource> save(Iterable<Hrmresource> entities);

    @Override
    public void delete(Long id);

    public List<Hrmresource> findByDepartmentid(Long departmentid);
}
