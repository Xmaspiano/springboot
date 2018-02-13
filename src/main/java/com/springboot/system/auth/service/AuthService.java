/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.springboot.system.auth.service;

import com.springboot.common.service.CommonService;
import com.springboot.system.auth.entity.firstDsE.Auth;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.resources.entity.firstDsE.Resources;

import java.util.List;

public interface AuthService extends CommonService<Auth> {

    public List<Auth> findByMenuidAndOrganizationId(Long menuid, Long organizationId);
    public List<Auth> findByMenuidAndUserId(Long menuid, Long userId);

    public List<Auth> findByUserId(Long userid);

    public List<Resources> findAllResByUser(Hrmresource user);
}
