package com.springboot.system.resources.model;

import com.springboot.system.resources.entity.firstDsE.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class effectRow {
    Long menuid = 0L;
    List<Resources> inserted;
    List<Resources> deleted;
    List<Resources> updated;
}
