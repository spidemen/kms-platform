package com.nbclass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nbclass.model.Permission;
import com.nbclass.model.Role;
import com.nbclass.model.User;
import com.nbclass.service.PermissionService;
import com.nbclass.service.RoleService;
import com.nbclass.shiro.MyShiroRealm;
import com.nbclass.util.CoreConst;
import com.nbclass.util.PageUtil;
import com.nbclass.util.ResultUtil;
import com.nbclass.vo.PermissionTreeListVo;
import com.nbclass.vo.base.PageResultVo;
import com.nbclass.vo.base.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController{
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MyShiroRealm myShiroRealm;

    /*角色列表入口*/
    @GetMapping("/list")
    public String roleList() {
        return "role/list";
    }

    /*角色列表数据*/
    @PostMapping("/list")
    @ResponseBody
    public PageResultVo pageRoles(Role role,Integer limit,Integer offset) {
        try {
            PageHelper.startPage(PageUtil.getPageNo(limit, offset),offset);
            List<Role> roleList = roleService.selectRoles(role);
            PageInfo<Role> pages = new PageInfo<>(roleList);
            return ResultUtil.table(roleList,pages.getTotal());
        } catch (Exception e) {
            logger.error(String.format("RoleController.loadRoles%s", e));
            throw e;
        }

    }

    /*新增角色*/
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo addRole(Role role) {
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
    @GetMapping("/delete")
    @ResponseBody
    public ResponseVo deleteRole(String roleId) {
        List<String> roleIdsList = Arrays.asList(roleId);
        int a = roleService.updateStatusBatch(roleIdsList, CoreConst.STATUS_INVALID);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("删除失败！");
        }
    }

    /*批量删除角色*/
    @GetMapping("/batch/delete")
    @ResponseBody
    public ResponseVo batchDeleteRole(String roleIdStr) {
        String[] roleIds = roleIdStr.split(",");
        List<String> roleIdsList = Arrays.asList(roleIds);
        int a = roleService.updateStatusBatch(roleIdsList,CoreConst.STATUS_INVALID);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("删除失败！");
        }
    }

    /*启用角色*/
    @GetMapping("/reuse")
    @ResponseBody
    public ResponseVo reuseRole(String roleIdStr) {
        String[] roleIds = roleIdStr.split(",");
        List<String> roleIdsList = Arrays.asList(roleIds);
        int a = roleService.updateStatusBatch(roleIdsList,1);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("启用失败！");
        }
    }

    /*角色详情*/
    @GetMapping("/detail")
    public String detail(Model model, Integer id, String opertype) {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        model.addAttribute("opertype", opertype);
        return "role/detail";
    }

    /*编辑角色*/
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo editRole(@ModelAttribute("role") Role role) {
        int a = roleService.updateByRoleId(role);
        if (a > 0) {
            return ResultUtil.success();
        } else {
            return ResultUtil.error("修改失败！");
        }
    }

    /*分配权限列表查询*/
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


    /*分配权限*/
    @PostMapping("/assign/permission")
    @ResponseBody
    public ResponseVo assignRole(String roleId, String permissionIdStr){
        List<String> permissionIdsList = new ArrayList<>();
        if(StringUtils.isNotBlank(permissionIdStr)){
            String[] permissionIds = permissionIdStr.split(",");
            permissionIdsList = Arrays.asList(permissionIds);
        }
        ResponseVo responseVo = roleService.addAssignPermission(roleId,permissionIdsList);
        /*重新加载角色下所有用户权限*/
        List<User> userList = roleService.findByRoleId(roleId);
        if(userList.size()>0){
            List<String> userIds = new ArrayList<>();
            for(User user : userList){
                userIds.add(user.getUserId());
            }
            myShiroRealm.clearAuthorizationByUserId(userIds);
        }
        return responseVo;
    }

}
