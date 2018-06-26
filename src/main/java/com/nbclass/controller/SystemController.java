package com.nbclass.controller;

import com.google.code.kaptcha.Constants;
import com.nbclass.annotation.BussinessLog;
import com.nbclass.model.Permission;
import com.nbclass.model.User;
import com.nbclass.service.PermissionService;
import com.nbclass.service.UserService;
import com.nbclass.util.CoreConst;
import com.nbclass.util.PasswordHelper;
import com.nbclass.util.ResultUtil;
import com.nbclass.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SystemController{

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    /*首页*/
    @RequestMapping(value={"/","/index"})
    public String index(){
        return "index/index";
    }

    /*工作台*/
    @GetMapping("/workdest")
    public String workdest(){
        return "index/workdest";
    }

    /*注册*/
    @GetMapping(value = "/register")
    public String register(){
        return "system/register";
    }

    /*提交注册*/
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request, User registerUser, String confirmPassword, String verification){
        //判断验证码
        String right_code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(right_code) && verification.equals(right_code)) {
            //验证码通过
        } else {
            return ResultUtil.error("验证码错误！");
        }
        String username = registerUser.getUsername();
        User user = userService.selectByUsername(username);
        if (null != user) {
            return ResultUtil.error("用户名已存在！");
        }
        String password = registerUser.getPassword();
        //判断两次输入密码是否相等
        if (confirmPassword != null && password != null) {
            if (!confirmPassword.equals(password)) {
                return ResultUtil.error("两次密码不一致！");
            }
        }
        registerUser.setUserId(UUIDUtil.getUniqueIdByUUId());
        registerUser.setStatus(CoreConst.STATUS_VALID);
        Date date = new Date();
        registerUser.setCreateTime(date);
        registerUser.setUpdateTime(date);
        registerUser.setLastLoginTime(date);
        PasswordHelper.encryptPassword(registerUser);
        //注册
        int registerResult = userService.register(registerUser);
        if(registerResult > 0){
            return ResultUtil.success("注册成功！");
        }else {
            return ResultUtil.error("注册失败，请稍后再试！");
        }
    }

    /*登陆*/
    @GetMapping("/login")
    public String login(Map map){
        return "system/login";
    }

    /*提交登录*/
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request, String username, String password, String verification,
                                     @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe){
        //判断验证码
        String right_code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(right_code) && verification.equals(right_code)) {
            //验证码通过
        } else {
            return ResultUtil.error("验证码错误！");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            token.setRememberMe(1 == rememberMe);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
        } catch (LockedAccountException e) {
            token.clear();
            return ResultUtil.error("用户已经被锁定不能登录，请联系管理员！");
        } catch (AuthenticationException e) {
            token.clear();
            return ResultUtil.error("用户名或者密码错误！");
        }
        //更新最后登录时间
        userService.updateLastLoginTime((User) SecurityUtils.getSubject().getPrincipal());
        return ResultUtil.success("登录成功！");
    }

    /*登出*/
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Map<String, Object> logout() {
        return ResultUtil.success();
    }

    /*获取当前登录用户的菜单*/
    @PostMapping("/menu")
    @ResponseBody
    public List<Permission> getMenus(){
        List<Permission> permissionListList = permissionService.selectMenuByUserId(((User) SecurityUtils.getSubject().getPrincipal()).getUserId());
        return permissionListList;
    }

}
