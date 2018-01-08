package com.springboot.system.service.impl;

import com.springboot.common.service.impl.CommonServiceImpl;
import com.springboot.system.entity.firstDsE.Favarite;
import com.springboot.system.repository.firstDs.FavariteRepository;
import com.springboot.system.service.FavariteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FavariteServiceImpl
        extends CommonServiceImpl<Favarite, FavariteRepository>
        implements FavariteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FavariteServiceImpl.class);

    @Override
//    @CachePut(value = "user_meun_favarite", key = "#language.name")//数据放入缓存
    public Favarite save(Favarite language) {
        return super.save(language);
    }

    @Override
    public Favarite findOne(Long id) {
        return super.findOne(id);
    }

    @Override
//    @Cacheable(value = "user_meun_favarite", key = "#name")//先从缓存中读取，如果没有再调用方法获取数据，然后把数据添加到缓存中
    public Favarite findByUserIdAndMenuId(Long id, Long menuId) {
        return getRepository().findByUserIdAndMenuId(id, menuId);
    }

    //    @CacheEvict(value = "user_meun_favarite", key = "#name") //移除指定key的数据
    public void deleteByUserIdAndMenuId(Long id, Long menuId){
        delete(findByUserIdAndMenuId(id, menuId).getId());
    }


    public List<Favarite> findByUserId(Long userId){
        return getRepository().findByUserId(userId);
    }

}