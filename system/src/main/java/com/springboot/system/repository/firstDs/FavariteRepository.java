package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.Favarite;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:29
 * @param   
 * @return   
 */  
public interface FavariteRepository
        extends JpaRepository<Favarite, Long>,CommonRepository<Favarite> {

    public Favarite findByUserIdAndMenuId(Long userId, Long menuId);

    public List<Favarite> findByUserId(Long userId);
}