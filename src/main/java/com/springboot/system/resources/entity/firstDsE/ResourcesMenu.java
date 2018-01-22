package com.springboot.system.resources.entity.firstDsE;

import com.springboot.common.entity.IdEntityMYSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by AlbertXmas on 17/8/29.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class ResourcesMenu extends IdEntityMYSQL {
    private Long menuid;
    private String keyname;
    /**
     * 是否显示 也表示是否可用 为了统一 都使用这个
     */
    private Boolean available = Boolean.FALSE;
}
