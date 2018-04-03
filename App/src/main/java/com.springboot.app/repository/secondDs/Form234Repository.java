package com.springboot.app.repository.secondDs;

import com.springboot.app.entity.secondDsE.Form234;
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
public interface Form234Repository
        extends JpaRepository<Form234, Long>,CommonRepository<Form234> {

    /**
     * 依据采购申请单号查询主数据
     * @param num
     * @return
     */
    List<Form234> findByNum(String num);
}
