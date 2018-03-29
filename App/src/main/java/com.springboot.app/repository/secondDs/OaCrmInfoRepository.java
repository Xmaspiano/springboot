package com.springboot.app.repository.secondDs;

import com.springboot.app.entity.secondDsE.OaCrmInfo;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:33
 * Created by IntelliJ IDEA.
 */
public interface OaCrmInfoRepository
        extends JpaRepository<OaCrmInfo, Long>,CommonRepository<OaCrmInfo> {
}
