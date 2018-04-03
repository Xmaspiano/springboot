package com.springboot.system.menu.service.impl;

import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.menu.entity.firstDsE.OsMenu;
import com.springboot.system.menu.repository.firstDs.OsMenuRepository;
import com.springboot.system.menu.service.OsMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
@Service
@Transactional(rollbackFor = Exception.class)
public class OsMenuServiceImpl
        extends BaseCommonServiceImpl<OsMenu, OsMenuRepository>
        implements OsMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsMenuServiceImpl.class);

    @Override
    //先从缓存中读取，如果没有再调用方法获取数据，然后把数据添加到缓存中
    @Cacheable(value = "MENU_LIST", key = "#id")
    public OsMenu findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    //数据放入缓存
    @CachePut(value = "MENU_LIST", key = "#osMenu.id")
    public OsMenu save(OsMenu osMenu) {
        return super.save(osMenu);
    }

    @Override
    //移除指定key的数据
    @CacheEvict(value = "MENU_LIST", key = "#id")
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public List<OsMenu> findAllBySuper(Long id){
        return getRepository().findByParentid(id);
    }

    @Override
    public List<OsMenu> findAllRealLife(){
        return getRepository().findAllByLife(true);
    }

    private void fullOsMenu(OsMenu osMenu){

    }

    private List<OsMenu> test(List<OsMenu> osMenuList){
        OsMenu osMenu;
        List<OsMenu> liTemp;
        for (int i = 0; i < osMenuList.size(); i++) {
            osMenu = osMenuList.get(i);
            liTemp = getRepository().findByParentid(osMenu.getId());
            if(liTemp.size()>0) {
                osMenu.setOsMenuList(test(liTemp));
            }
        }
        return osMenuList;
    }

}