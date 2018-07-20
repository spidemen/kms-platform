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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/online/user")
public class OnlineUserController {
    @Autowired
    private UserService userService;

    // 在线用户列表
    @PostMapping("/list")
    @ResponseBody
    public PageResultVo onlineUsers(UserOnlineVo user, Integer limit, Integer offset){
        List<UserOnlineVo> userList = userService.selectOnlineUsers(user);
        int endIndex = (offset+limit) > userList.size() ? userList.size() : (offset+limit);
        return ResultUtil.table(userList.subList(offset,endIndex),(long)userList.size());
    }

    // 强制踢出用户
    @PostMapping("/kickout")
    @ResponseBody
    public ResponseVo kickout(String sessionId,String username) {
        try {
            userService.kickout(sessionId,username);
            return ResultUtil.success("踢出用户成功");
        } catch (Exception e) {
            return ResultUtil.error("踢出用户失败");
        }
    }

    // 批量强制踢出用户
    @PostMapping("/batch/kickout")
    @ResponseBody
    public ResponseVo kickout(@RequestParam(value = "sessionIds[]") String[] sessionIds) {
        try {
            for (String sessionId : sessionIds) {
                userService.kickout(sessionId,"");
            }
            return ResultUtil.success("踢出用户成功");
        } catch (Exception e) {
            return ResultUtil.error("踢出用户失败");
        }
    }
}
