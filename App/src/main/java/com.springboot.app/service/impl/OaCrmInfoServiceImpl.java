package com.springboot.app.service.impl;

import com.springboot.app.entity.secondDsE.OaCrmInfo;
import com.springboot.app.repository.secondDs.OaCrmInfoRepository;
import com.springboot.app.service.OaCrmInfoService;
import com.springboot.common.service.impl.BaseCommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:36
 * Created by IntelliJ IDEA.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OaCrmInfoServiceImpl
        extends BaseCommonServiceImpl<OaCrmInfo, OaCrmInfoRepository>
        implements OaCrmInfoService {
}
