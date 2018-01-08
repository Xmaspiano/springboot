package com.springboot.system.entity.firstDsE;

import com.springboot.common.entity.IdEntityMYSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**
 * Created by AlbertXmas on 17/8/29.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ShiroResources extends IdEntityMYSQL {

    private String keyname = "";
    private String realName = "";
    private String name = "";
    private String method = "" ;
    private String shiroAuth = "";
    private boolean available = false;

}
