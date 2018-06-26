package com.nbclass.mapper;

import com.nbclass.model.UserRole;
import com.nbclass.util.MyMapper;

import java.util.List;

public interface UserRoleMapper extends MyMapper<UserRole> {
    public List<String> findUserIdByRoleId(String roleId);
}