package com.nbclass.mapper;

import com.nbclass.model.Permission;
import com.nbclass.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface PermissionMapper extends MyMapper<Permission> {

    /*
     *查询全部权限
     */
    List<Permission> selectAllPerms(Integer status);

    List<Permission> selectAllMenuName(Integer status);

    Set<String> findPermissionsByUserId(String userId);

    List<Permission> findByRoleId(String roleId);

    List<Permission> selectByUserId(String userId);

    List<Permission> selectMenuByUserId(String userId);

    int updateStatusByPermissionId(@Param("permissionId") String permissionId, @Param("status") Integer status);

    Permission selectByPermissionId(String permissionId);

    int updateByPermissionId(Permission permission);

    int selectSubPermsByPermissionId(String permissionId);
}