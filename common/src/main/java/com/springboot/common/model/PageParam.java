package com.springboot.common.model;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * 分页菜单初始化配置
 * @author XmasPiano
 * @date 2018/3/27 - 上午9:51
 * Created by IntelliJ IDEA.
 */
@Data
public class PageParam {
    private int page = 1;
    private int rows = 30;
}
