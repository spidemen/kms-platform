package com.nbclass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbclass.annotation.BussinessLog;
import com.nbclass.model.Permission;
import com.nbclass.model.Role;
import com.nbclass.model.User;
import com.nbclass.service.PermissionService;
import com.nbclass.service.RoleService;
import com.nbclass.shiro.MyShiroRealm;
import com.nbclass.shiro.ShiroService;
import com.nbclass.util.ResultUtil;
import com.nbclass.vo.PermissionTreeListVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/role")
public class RoleController{
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private MyShiroRealm myShiroRealm;

    /*角色列表入口*/
    @BussinessLog(value="查看角色列表")
    @GetMapping("/list")
    public String roleList() {
        return "role/list";
    }

    /*角色列表数据*/
    @PostMapping("/list")
    @ResponseBody
    public Map<String, Object> loadRoles(Integer limit, Integer offset, Integer status) {
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            int pageNo = 0;
            if (offset != 0) {
                pageNo = offset / limit;
            }
            pageNo += 1;
            PageHelper.startPage(pageNo, limit);
            params.put("status",status);
            List<Role> roleList = roleService.selectAllRoles(params);
            PageInfo<Role> pages = new PageInfo<>(roleList);
            return ResultUtil.table(roleList,pages.getTotal());
        } catch (Exception e) {
            logger.error(String.format("RoleController.loadRoles%s", e));
            throw e;
        }

    }

    /*新增角色*/
    @BussinessLog(value="新增角色")
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> addRole(Role role) {
        try {
            int a = roleService.insert(role);
            if (a > 0) {
                return ResultUtil.success();
            } else {
                return ResultUtil.error("保存失败！");
            }
        } catch (Exception e) {
            logger.error(String.format("RoleController.addRole%s", e));
            throw e;
        }
    }

    /*删除角色*/
    @BussinessLog(value="删除角色")
    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> deleteRole(String roleIdStr) {
        String[] roleIds = roleIdStr.split(",");
        List<String> roleIdsList = Arrays.asList(roleIds);
        int a = roleService.updateStatusBatch(roleIdsList,2);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("删除失败！");
        }
    }

    /*启用角色*/
    @BussinessLog(value="启用角色")
    @GetMapping("/reuse")
    @ResponseBody
    public Map<String, Object> reuseRole(String roleIdStr) {
        String[] roleIds = roleIdStr.split(",");
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        List<String> roleIdsList = Arrays.asList(roleIds);
        int a = roleService.updateStatusBatch(roleIdsList,1);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("启用失败！");
        }
    }

    /*角色详情*/
    @BussinessLog(value="查看角色详情")
    @GetMapping("/detail")
    public String detail(Model model, Integer id, String opertype) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        model.addAttribute("opertype", opertype);
        return "role/detail";
    }

    /*编辑角色*/
    @BussinessLog(value="编辑角色")
    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> editRole(@ModelAttribute("role") Role role) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        int a = roleService.updateByRoleId(role);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("修改失败！");
        }
    }

    /*分配权限列表查询*/
    @BussinessLog(value="查看分配权限列表")
    @PostMapping("/assign/permission/list")
    @ResponseBody
    public List<PermissionTreeListVo> assignRole(String roleId){
        List<PermissionTreeListVo> listVos = new ArrayList<>();
        List<Permission> allPermissions = permissionService.selectAll(1);
        List<Permission> hasPermissions = roleService.findPermissionsByRoleId(roleId);
        for(Permission permission : allPermissions){
            PermissionTreeListVo vo = new PermissionTreeListVo();
            vo.setId(permission.getId());
            vo.setPermissionId(permission.getPermissionId());
            vo.setName(permission.getName());
            vo.setParentId(permission.getParentId());
            for(Permission hasPermission:hasPermissions){
                //有权限则选中
                if(hasPermission.getPermissionId().equals(permission.getPermissionId())){
                    vo.setChecked(true);
                    break;
                }
            }
            listVos.add(vo);
        }
        return listVos;
    }


    @BussinessLog(value="保存分配权限")
    /*分配权限*/
    @PostMapping("/assign/permission")
    @ResponseBody
    public Map<String,Object> assignRole(String roleId,String permissionIdStr){
        List<String> permissionIdsList = new ArrayList<>();
        if(StringUtils.isNotBlank(permissionIdStr)){
            String[] permissionIds = permissionIdStr.split(",");
            permissionIdsList = Arrays.asList(permissionIds);
        }
        Map<String,Object> jsonMap = roleService.addAssignPermission(roleId,permissionIdsList);
        /*重新加载角色下所有用户权限*/
        List<User> userList = roleService.findByRoleId(roleId);
        if(userList.size()>0){
            List<String> userIds = new ArrayList<>();
            for(User user : userList){
                userIds.add(user.getUserId());
            }
            myShiroRealm.clearAuthorizationByUserId(userIds);
        }
        return jsonMap;
    }

}
