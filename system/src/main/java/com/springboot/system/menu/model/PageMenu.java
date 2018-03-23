package com.springboot.system.menu.model;

import com.springboot.common.model.BaseTreeModel;
import com.springboot.common.model.ParentId;
import com.springboot.system.menu.entity.firstDsE.OsMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("PageMenu")
public class PageMenu extends BaseTreeModel<PageMenu,OsMenu> {
    private Long id;
    private String appid;
    private String name;
    @ParentId
    private String parentid;
    private String remark;
    private String datemark;
    private boolean life;

    private List<OsMenu> osMenuList = new ArrayList<OsMenu>();
}
