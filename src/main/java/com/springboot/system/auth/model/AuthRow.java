package com.springboot.system.auth.model;

import com.springboot.system.resources.entity.firstDsE.Resources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRow {
    Long menuid = 0L;
    Long deptid = 0L;
    Long roleid = 0L;
    Long userid = 0L;
    Long groupid = 0L;
    String typeAuth = "";

    List<Resources> checked;
}
