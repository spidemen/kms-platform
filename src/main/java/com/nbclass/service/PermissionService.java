package com.nbclass.service;

import com.nbclass.model.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    Set<String> findPermissionsByUserId(String userId);

    List<Permission> selectAll(Integer status);

    List<Permission> selectAllMenuName(Integer status);

    List<Permission> selectByUserId(String userId);

    List<Permission> selectMenuByUserId(String userId);

    int insert(Permission permission);

    int updateStatus(String permissionId, Integer status);

    Permission findByPermissionId(String permissionId);

    Permission findById(Integer id);

    int updateByPermissionId(Permission permission);

    List<Permission> selectSubPermsByPermissionId(String permissionId);
}
