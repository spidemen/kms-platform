package com.nbclass.mapper;

import com.nbclass.model.Role;
import com.nbclass.util.MyMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleMapper extends MyMapper<Role> {
    Set<String> findRoleByUserId(String userId);

    //批量修改状态
    public int updateStatusBatch(Map<String, Object> params);

    int updateByRoleId(Map<String, Object> params);

    List<Role> findByStatus(Integer status);



}