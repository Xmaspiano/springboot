package com.springboot.system.resources.model;

import com.springboot.system.resources.entity.firstDsE.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:26
 * @param   
 * @return   
 */  
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EffectRow {
    Long menuid = 0L;
    List<Resources> inserted;
    List<Resources> deleted;
    List<Resources> updated;
}
