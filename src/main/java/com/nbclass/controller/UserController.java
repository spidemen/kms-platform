package com.nbclass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbclass.annotation.BussinessLog;
import com.nbclass.model.Role;
import com.nbclass.model.User;
import com.nbclass.service.RoleService;
import com.nbclass.service.UserService;
import com.nbclass.shiro.MyShiroRealm;
import com.nbclass.shiro.ShiroService;
import com.nbclass.util.*;
import com.nbclass.vo.ChangePasswordVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ShiroService shiroService;

    /*用户列表入口*/
    @BussinessLog(value="查看用户列表")
    @GetMapping("/list")
    public String userList(){
        return "user/list";
    }

    /*用户列表数据*/
    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> loadUsers(Integer limit, Integer offset,Integer status){
        Map<String, Object> params = new HashMap<String, Object>();
        int pageNo = 0;
        if (offset != 0) {
            pageNo = offset / limit;
        }
        pageNo += 1;
        PageHelper.startPage(pageNo, limit);
        params.put("status",status);
        List<User> userList = userService.selectAllUsers(params);
        PageInfo<User> pages = new PageInfo<>(userList);
        return ResultUtil.table(userList,pages.getTotal());
    }

    /*新增用户*/
    @BussinessLog(value="新增用户")
    @PostMapping("/add")
    @ResponseBody
    public Map<String,Object> add(User userForm, String confirmPassword){
        String username = userForm.getUsername();
        User user = userService.selectByUsername(username);
        if (null != user) {
            return ResultUtil.error("用户名已存在");
        }
        String password = userForm.getPassword();
        //判断两次输入密码是否相等
        if (confirmPassword != null && password != null) {
            if (!confirmPassword.equals(password)) {
                return ResultUtil.error("两次密码不一致");
            }
        }
        userForm.setUserId(UUIDUtil.getUniqueIdByUUId());
        userForm.setStatus(CoreConst.STATUS_VALID);
        Date date = new Date();
        userForm.setCreateTime(date);
        userForm.setUpdateTime(date);
        userForm.setLastLoginTime(date);
        PasswordHelper.encryptPassword(userForm);
        int num = userService.register(userForm);
        if(num > 0){
            return ResultUtil.success("添加用户成功");
        }else {
            return ResultUtil.error("添加用户失败");
        }
    }

    /*用户详情*/
    @BussinessLog(value="查看用户详情")
    @GetMapping("/detail")
    public String userDetail(Model model, String userId, String opertype){
        User user = userService.selectByUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("opertype", opertype);
        return "user/userDetail";
    }

    /*编辑用户*/
    @BussinessLog(value="编辑用户")
    @PostMapping("/edit")
    @ResponseBody
    public Map<String,Object> editUser(User userForm){
        int a = userService.updateByUserId(userForm);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("修改失败");
        }
    }

    /*删除用户*/
    @BussinessLog(value="删除用户")
    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteUser(String userIdStr) {
        String[] userIds = userIdStr.split(",");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        List<String> userIdsList = Arrays.asList(userIds);
        int a = userService.updateStatusBatch(userIdsList,2);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("删除失败");
        }
    }

    /*启用*/
    @BussinessLog(value="启用用户")
    @GetMapping("/reuse")
    @ResponseBody
    public Map<String, Object> reuseUser(String userIdStr) {
        String[] userIds = userIdStr.split(",");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        List<String> userIdsList = Arrays.asList(userIds);
        int a = userService.updateStatusBatch(userIdsList,1);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("启用失败");
        }
    }

    /*分配角色列表查询*/
    @BussinessLog(value="查询分配角色列表")
    @PostMapping("/assign/role/list")
    @ResponseBody
    public Map<String,Object> assignRoleList(String userId){
        List<Role> roleList = roleService.selectRolesByStatus(1);
        Set<String> hasRoles = roleService.findRoleByUserId(userId);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", roleList);
        jsonMap.put("hasRoles",hasRoles);
        return jsonMap;
    }

    /*保存分配角色*/
    @BussinessLog(value="保存分配角色")
    @PostMapping("/assign/role")
    @ResponseBody
    public Map<String,Object> assignRole(String userId,String roleIdStr){
        String[] roleIds = roleIdStr.split(",");
        List<String> roleIdsList = Arrays.asList(roleIds);
        Map<String,Object> jsonMap = userService.addAssignRole(userId,roleIdsList);
        shiroService.reloadAuthorizingByUserId(userService.selectByUserId(userId));
        return jsonMap;
    }

    @BussinessLog(value="修改密码")
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePassword(ChangePasswordVo changePasswordVo) {
        Map<String, String> result = new HashMap<String, String>();
        if(!changePasswordVo.getNewPassword().equals(changePasswordVo.getConfirmNewPassword())){
            return ResultUtil.error("两次密码输入不一致");
        }
        User loginUser = ((User) SecurityUtils.getSubject().getPrincipal());
        User newUser = CopyUtil.getCopy(loginUser,User.class);
        String sysOldPassword = loginUser.getPassword();
        newUser.setPassword(changePasswordVo.getOldPassword());
        String entryOldPassword = PasswordHelper.getPassword(newUser);
        if(sysOldPassword.equals(entryOldPassword)){
            newUser.setPassword(changePasswordVo.getNewPassword());
            PasswordHelper.encryptPassword(newUser);
            userService.updateUserByPrimaryKey(newUser);
            //*清除登录缓存*//
            RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            MyShiroRealm shiroRealm = (MyShiroRealm) rsm.getRealms().iterator().next();
            shiroRealm.removeCachedAuthenticationInfo((User) SecurityUtils.getSubject().getPrincipal());
            SecurityUtils.getSubject().logout();
        }else{
            return ResultUtil.error("您输入的旧密码有误");
        }
        return ResultUtil.success();
    }
}
