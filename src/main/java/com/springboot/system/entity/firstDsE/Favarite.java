package com.springboot.system.entity.firstDsE;


import com.springboot.common.entity.IdEntityMYSQL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

/**
 * Created by AlbertXmas on 17/9/7.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Favarite extends IdEntityMYSQL {
    private Long userId;
    private Long menuId;
    private String sort;
}
