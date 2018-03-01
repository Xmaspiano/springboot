/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.springboot.system.auth.service.impl;

import com.springboot.common.service.impl.BaseCommonServiceImpl;
import com.springboot.system.auth.entity.firstDsE.Auth;
import com.springboot.system.auth.repository.firstDs.AuthRepository;
import com.springboot.system.auth.service.AuthService;
import com.springboot.system.entity.secondDsE.Hrmresource;
import com.springboot.system.resources.entity.firstDsE.Resources;
import com.springboot.system.resources.service.ResourcesService;
import com.springboot.system.service.HrmdepartmentService;
import com.springboot.system.service.HrmresourceService;
import com.springboot.system.util.ShiroCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuthServiceImpl
        extends BaseCommonServiceImpl<Auth, AuthRepository>
        implements AuthService {

    @Autowired
    HrmdepartmentService hrmdepartmentService;

    @Autowired
    ResourcesService resourcesService;

    @Autowired
    HrmresourceService hrmresourceService;

    @Autowired
    ShiroCacheUtil shiroCacheUtil;

    @Override
    public List<Auth> findByMenuidAndOrganizationId(Long menuid, Long organizationId) {
        return getRepository().findByMenuidAndOrganizationId(menuid, organizationId);
    }

    @Override
    public List<Auth> findByMenuidAndUserId(Long menuid, Long userId) {
        return getRepository().findByMenuidAndUserId(menuid, userId);
    }

    @Override
    public List<Auth> findByUserId(Long userid) {
        return getRepository().findByUserId(userid);
    }

    @Override
    public Auth save(Auth e){
        e = super.save(e);
        shiroCacheUtil.removeCacheByUser(Arrays.asList(e));
        return e ;
    }

    @Override
    public Iterable<Auth> save(Iterable<Auth> entities){
        entities = super.save(entities);
        shiroCacheUtil.removeCacheByUser(entities);
        return entities;
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        shiroCacheUtil.removeCacheByUser(Arrays.asList(
                getRepository().findOne(id)
        ));
    }

    @Override
    public void delete(Iterable<Auth> entities) {
        super.delete(entities);
        shiroCacheUtil.removeCacheByUser(entities);
    }



    @Override
    public List<Resources> findAllResByUser(Hrmresource user) {
        Long userid = user.getId();
        Long dept = user.getDepartmentid();

        Set<Long> setDepts = new HashSet();
        Set<Long> setgroups = new HashSet();
        Set<Long> setjobs = new HashSet();

        setDepts.add(dept);

        List<Resources> resources = new ArrayList();
        getRepository().fingKeyNameByUser(userid, setgroups, setDepts, setjobs).forEach(
                keyName ->resources.add(resourcesService.findByKeyname(keyName))
        );

        return resources;
    }


}
