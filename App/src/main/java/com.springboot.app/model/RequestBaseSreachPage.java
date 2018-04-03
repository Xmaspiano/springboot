package com.springboot.app.model;

import com.springboot.common.model.PageParam;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/4/2 - 下午3:28
 * Created by IntelliJ IDEA.
 */
@Data
public class RequestBaseSreachPage extends PageParam {
    String subcompanyid;
    String workflowid;
    String creater;
    String strDate;
    String endDate;
    String orderid;
    String sreachname;
}
