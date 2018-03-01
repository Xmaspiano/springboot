package com.springboot.system.menu.repository.firstDs;

import com.springboot.system.menu.entity.firstDsE.OsMenu;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
public interface OsMenuRepository extends JpaRepository<OsMenu,Long>, CommonRepository<OsMenu> {

    List<OsMenu> findByParentid(Long parentid);

    List<OsMenu> findAllByLife(Boolean life);
}
