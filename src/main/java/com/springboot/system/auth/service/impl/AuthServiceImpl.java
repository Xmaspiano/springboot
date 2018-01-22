/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.springboot.system.auth.service.impl;

import com.springboot.common.service.impl.CommonServiceImpl;
import com.springboot.system.auth.entity.firstDsE.Auth;
import com.springboot.system.auth.repository.firstDs.AuthRepository;
import com.springboot.system.auth.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
@Transactional
public class AuthServiceImpl
        extends CommonServiceImpl<Auth, AuthRepository>
        implements AuthService {

    @Override
    public List<Auth> findByMenuidAndOrganizationId(Long menuid, Long organizationId) {
        return getRepository().findByMenuidAndOrganizationId(menuid, organizationId);
    }

    @Override
    public List<Auth> findByUserId(Long userid) {
        return getRepository().findByUserId(userid);
    }
}
