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
 * @date 2018/3/30 - 上午9:33
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "FORMTABLE_MAIN_54_DT1")
public class Form54dt1 extends BaseIdEntity<Form54dt1> {
    String mainid;
    String plant;
    String stgeloc;
    String materialname;
    String cgsl;
    String lywl2;
    String lywl;
    String sfgb;

}
