package com.nbclass.service;

import com.nbclass.model.User;
import com.nbclass.vo.base.ResponseVo;

import java.util.List;
import java.util.Map;

public interface UserService {

    User selectByUsername(String username);

    int register(User user);

    void updateLastLoginTime(User loginUser);

    List<User> selectUsers(User user);

    User selectByUserId(String userId);

    int updateByUserId(User user);

    int updateStatusBatch(List<String> userIds, Integer status);

    ResponseVo addAssignRole(String userId, List<String> roleIds);

    int updateUserByPrimaryKey(User user);

}
