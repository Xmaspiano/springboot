package com.springboot.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/6 - 下午4:42
 * Created by IntelliJ IDEA.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavariteMenu {
    Long id;
    Long menuId;
    String sort;
    String text;
}
