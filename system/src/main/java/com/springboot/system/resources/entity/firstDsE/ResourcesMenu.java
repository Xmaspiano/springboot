package com.springboot.system.resources.entity.firstDsE;

import com.springboot.common.entity.BaseIdEntityMysql;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:27
 * @param   
 * @return   
 */  
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ResourcesMenu extends BaseIdEntityMysql {
    private Long menuid;
    private String keyname;
    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    private Boolean available = Boolean.FALSE;
}
