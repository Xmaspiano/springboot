package com.springboot.app.entity.secondDsE;

import com.springboot.common.entity.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:28
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "YX_GYS_INFO_SEARCH")
@SequenceGenerator(name = "YX_GYS_INFO_SEARCH_ID",sequenceName = "YX_GYS_INFO_SEARCH_ID")
public class OaCrmInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YX_GYS_INFO_SEARCH_ID")
    private Long id;
    private String gysbh;
    private String ekor;
    private String name;
}
