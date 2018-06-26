package com.nbclass.service;

import com.nbclass.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User selectByUsername(String username);

    int register(User user);

    void updateLastLoginTime(User loginUser);

    List<User> selectAllUsers(Map<String, Object> params);

    User selectByUserId(String userId);

    int updateByUserId(User user);

    int updateStatusBatch(List<String> userIds, Integer status);

    Map<String,Object> addAssignRole(String userId, List<String> roleIds);

    int updateUserByPrimaryKey(User user);

}
