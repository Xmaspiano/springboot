package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.OsMenu;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OsMenuRepository extends JpaRepository<OsMenu,Long>, CommonRepository<OsMenu> {

    List<OsMenu> findByParentid(long parentid);

    List<OsMenu> findAllByLife(Boolean life);
}
