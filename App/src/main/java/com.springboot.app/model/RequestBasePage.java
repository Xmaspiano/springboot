package com.springboot.app.model;

import com.springboot.app.entity.secondDsE.RequestBase;
import com.springboot.common.model.BaseModel;
import com.springboot.common.model.PageParam;
import com.springboot.system.util.Common;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/4/2 - 下午3:28
 * Created by IntelliJ IDEA.
 */
@Data
@Component("RequestBasePage")
public class RequestBasePage extends BaseModel<RequestBasePage, RequestBase> {
    Long requestid;
    String workflowid;
    String currentnodetype;
    String requestname;

    String creater;
    String lasterName;
    String createdate;
    Long mainrequestid;
}
