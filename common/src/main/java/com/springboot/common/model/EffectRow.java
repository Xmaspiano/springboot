package com.springboot.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 *
 * @author XmasPiano
 * @date 2018/3/1 上午10:26
 * @param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EffectRow<E> {
    Long menuid = 0L;
    List<E> inserted;
    List<E> deleted;
    List<E> updated;
}
