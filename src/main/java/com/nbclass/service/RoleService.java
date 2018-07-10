package com.nbclass.service;

import com.nbclass.model.Permission;
import com.nbclass.model.Role;
import com.nbclass.model.User;
import com.nbclass.vo.base.ResponseVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService {

    Set<String> findRoleByUserId(String userId);

    List<Role> selectRoles(Role role);

    int insert(Role role);

    int updateStatusBatch(List<String> roleIds, Integer status);

    Role findById(Integer id);

    int updateByRoleId(Role role);

    List<Role> selectRolesByStatus(Integer status);

    List<Permission> findPermissionsByRoleId(String roleId);

    ResponseVo addAssignPermission(String roleId, List<String> permissionIdsList);

    List<User> findByRoleId(String roleId);

}
