package com.springboot.system.resources.repository.firstDs;

        import com.springboot.system.resources.entity.firstDsE.ResourcesMenu;
        import com.springboot.common.repository.CommonRepository;
        import org.springframework.data.repository.CrudRepository;

        import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:27
 * @param   
 * @return   
 */  
public interface ResourcesMenuRepository
        extends CrudRepository<ResourcesMenu, Long>,CommonRepository<ResourcesMenu> {
    public List<ResourcesMenu> findByMenuid(Long menuid);

    void deleteByMenuidAndKeyname(Long menuid, String keyname);
}
