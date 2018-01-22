package com.springboot.system.service;


        import com.springboot.common.service.CommonService;
        import com.springboot.system.entity.secondDsE.Hrmdepartment;

        import java.util.List;

/**
 * Created by AlbertXmas on 2017/1/13.
 */

public interface HrmdepartmentService extends CommonService<Hrmdepartment> {
    public List<Hrmdepartment> findByYXDept();

    public List<Hrmdepartment> findAllBySuper(Long id);
}
