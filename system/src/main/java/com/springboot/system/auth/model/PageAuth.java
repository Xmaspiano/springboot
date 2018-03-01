package com.springboot.system.auth.model;

import com.springboot.common.model.BaseCheckboxModel;
import com.springboot.system.resources.entity.firstDsE.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("PageAuth")
public class PageAuth extends BaseCheckboxModel<PageAuth, Resources> {
    String id;
    String keyname;
    String realName;
    String name;
    String method;
    String shiroAuth;
    String available;

    /**
     * 组织机构
     */
    private Long organizationId = 0L;

    /**
     * 工作职务
     */
    private Long jobId = 0L;

    /**
     * 用户
     */
    private Long userId = 0L;

    /**
     * 组
     */
    private Long groupId = 0L;

    @Override
    public void setChecked(Object obj) {

    }
}
