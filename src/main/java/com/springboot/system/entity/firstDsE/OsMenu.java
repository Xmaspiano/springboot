package com.springboot.system.entity.firstDsE;


import com.springboot.system.entity.base.IdEntity;
import com.springboot.system.entity.base.IdEntityMYSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlbertXmas on 17/8/8.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class OsMenu extends IdEntityMYSQL {
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
