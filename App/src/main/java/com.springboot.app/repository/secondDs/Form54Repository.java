package com.springboot.app.repository.secondDs;

import com.springboot.app.entity.secondDsE.Form54;
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
public interface Form54Repository
        extends JpaRepository<Form54, Long>,CommonRepository<Form54> {

    /**
     * 依据预留号查询流程主数据
     * @param reservation
     * @return
     */
    List<Form54> findByReservation(String reservation);
}
