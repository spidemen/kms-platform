package com.nbclass.controller;

import com.nbclass.service.UserService;
import com.nbclass.util.ResultUtil;
import com.nbclass.vo.UserOnlineVo;
import com.nbclass.vo.base.PageResultVo;
import com.nbclass.vo.base.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/onlineUser")
public class OnlineUserController {
    @Autowired
    private UserService userService;

    // 在线用户列表
    @PostMapping(value = "list")
    @ResponseBody
    public PageResultVo onlineUsers(UserOnlineVo user, Integer limit, Integer offset){
        List<UserOnlineVo> userList = userService.selectOnlineUsers(user);
        int endIndex = (offset+limit) > userList.size() ? userList.size() : (offset+limit);
        return ResultUtil.table(userList.subList(offset,endIndex),(long)userList.size());
    }

    // 强制踢出用户
    @PostMapping(value = "kickout")
    @ResponseBody
    public ResponseVo kickout(String sessionId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            userService.kickout(sessionId);
            return ResultUtil.success("踢出用户成功");
        } catch (Exception e) {
            return ResultUtil.error("踢出用户失败");
        }
    }
}
