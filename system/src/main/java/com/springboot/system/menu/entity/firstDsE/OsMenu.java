package com.springboot.system.menu.entity.firstDsE;


import com.springboot.common.entity.BaseIdEntityMysql;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:24
 * @param   
 * @return   
 */  
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class OsMenu extends BaseIdEntityMysql {
    private String appid;
    private String name;
    private Long parentid;
    private String remark;
    private Date datemark;
    private boolean life;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid", updatable = false)
    private List<OsMenu> osMenuList = new ArrayList<OsMenu>();
}
