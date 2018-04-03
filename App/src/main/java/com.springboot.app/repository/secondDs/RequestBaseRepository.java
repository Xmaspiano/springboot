package com.springboot.app.repository.secondDs;

import com.springboot.app.entity.secondDsE.RequestBase;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:33
 * Created by IntelliJ IDEA.
 */
public interface RequestBaseRepository
        extends JpaRepository<RequestBase, Long>,CommonRepository<RequestBase> {

    List<RequestBase> findByMainrequestid(Long requestid);
}
