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
 * @date 2018/3/1 上午10:26
 * @param
 * @return
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Resources extends BaseIdEntityMysql {
    private String keyname = "";
    private String realName = "";
    private String name = "";
    private String method = "" ;
    private String shiroAuth = "";
    private boolean available = false;
}
