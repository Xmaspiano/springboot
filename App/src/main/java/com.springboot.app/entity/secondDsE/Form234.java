package com.springboot.app.entity.secondDsE;

import com.springboot.common.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/30 - 上午9:32
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "FORMTABLE_MAIN_234")
public class Form234 extends BaseIdEntity<Form234>{
    String requestid;
    String sqr;
    String sqrbm;
    String nd;
    String num;
    String movetype;
    String costcenter;
    String sfgb;
}
