package com.springboot.system.repository.firstDs;

import com.springboot.system.entity.firstDsE.Favarite;
import com.springboot.common.repository.CommonRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavariteRepository
        extends JpaRepository<Favarite, Long>,CommonRepository<Favarite> {

    public Favarite findByUserIdAndMenuId(Long userId, Long menuId);

    public List<Favarite> findByUserId(Long userId);
}