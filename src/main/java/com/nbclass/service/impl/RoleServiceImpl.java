package com.nbclass.service.impl;

import com.nbclass.mapper.*;
import com.nbclass.model.Permission;
import com.nbclass.model.Role;
import com.nbclass.model.RolePermission;
import com.nbclass.model.User;
import com.nbclass.service.RoleService;
import com.nbclass.util.ResultUtil;
import com.nbclass.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Set<String> findRoleByUserId(String userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    @Override
    public List<Role> selectAllRoles(Map<String, Object> params) {
        Role role = new Role();
        role.setStatus((Integer) params.get("status"));
        return roleMapper.select(role);
    }

    @Override
    public int insert(Role role) {
        role.setRoleId(UUIDUtil.getUniqueIdByUUId());
        role.setStatus(1);
        role.setCreateTime(new Date());
        return roleMapper.insert(role);
    }

    @Override
    public int updateStatusBatch(List<String> roleIds, Integer status) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("roleIds",roleIds);
        params.put("status",status);
        return roleMapper.updateStatusBatch(params);
    }

    @Override
    public Role findById(Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleMapper.selectByPrimaryKey(role);
    }

    @Override
    public int updateByRoleId(Role role) {
        Map<String,Object> params  = new HashMap<>();
        params.put("name",role.getName());
        params.put("description",role.getDescription());
        params.put("role_id",role.getRoleId());
        return roleMapper.updateByRoleId(params);
    }

    @Override
    public List<Role> selectRolesByStatus(Integer status) {
        return roleMapper.findByStatus(status);
    }

    @Override
    public List<Permission> findPermissionsByRoleId(String roleId) {
        return permissionMapper.findByRoleId(roleId);
    }

    @Override
    public Map<String, Object> addAssignPermission(String roleId, List<String> permissionIds) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        try{
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermissionMapper.delete(rolePermission);
            for(String permissionId : permissionIds){
                rolePermission.setId(null);
                rolePermission.setPermissionId(permissionId);
                rolePermissionMapper.insert(rolePermission);
            }
            return ResultUtil.success();
        }catch(Exception e){
            return ResultUtil.error("启用失败！");
        }
    }

    @Override
    public List<User> findByRoleId(String roleId) {
        return userMapper.findByRoleId(roleId);
    }
}
