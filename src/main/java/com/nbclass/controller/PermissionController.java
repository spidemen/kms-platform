package com.nbclass.controller;

import com.nbclass.model.Permission;
import com.nbclass.service.PermissionService;
import com.nbclass.shiro.ShiroService;
import com.nbclass.util.CoreConst;
import com.nbclass.util.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController{
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ShiroService shiroService;

    /*权限列表入口*/
    @GetMapping("/list")
    public String permissionList(){
        return "permission/list";
    }

    /*权限列表数据*/
    @PostMapping("/list")
    @ResponseBody
    public List<Permission>  loadPermissions(HttpServletRequest request, HttpServletResponse response,String flag){
        List<Permission> permissionListList = new ArrayList<Permission>();
        if(StringUtils.isBlank(flag) || "1".equals(flag)){
            permissionListList = permissionService.selectAll(CoreConst.STATUS_VALID);
        }else if("2".equals(flag)){
            permissionListList = permissionService.selectAllMenuName(CoreConst.STATUS_VALID);
        }
        return permissionListList;
    }

    /*添加权限*/
    @ResponseBody
    @PostMapping("/add")
    public Map<String, Object> addPermission(Permission permission){
        try {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            int a = permissionService.insert(permission);
            if (a > 0) {
                shiroService.updatePermission();
                return ResultUtil.success();
            } else {
                return ResultUtil.error("保存失败！");
            }
        } catch (Exception e) {
            logger.error(String.format("PermissionController.addPermission%s", e));
            throw e;
        }
    }

    /*删除权限*/
    @ResponseBody
    @PostMapping("/delete")
    public Map<String, Object> deletePermission(String permissionId){
        try {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            int subPermsByPermissionIdCount = permissionService.selectSubPermsByPermissionId(permissionId);
            if(subPermsByPermissionIdCount>0){
                return ResultUtil.error("改资源存在下级资源，无法删除！");
            }
            int a = permissionService.updateStatus(permissionId,CoreConst.STATUS_INVALID);
            if (a > 0) {
                shiroService.updatePermission();
                return ResultUtil.success();
            } else {
                return ResultUtil.error("删除失败！");
            }
        } catch (Exception e) {
            logger.error(String.format("PermissionController.deletePermission%s", e));
            throw e;
        }
    }

    /*权限详情*/
    @GetMapping("/detail")
    public String detail(Model model, String permissionId, String opertype) {
        Permission permission = permissionService.findByPermissionId(permissionId);
        if(null!=permission){
            if(permission.getParentId().equals(CoreConst.top_menu_id)){
                model.addAttribute("parentName", CoreConst.top_menu_name);
            }else{
                Permission parent = permissionService.findById(permission.getParentId());
                model.addAttribute("parentName", parent.getName());
            }
        }
        model.addAttribute("permission", permission);
        model.addAttribute("opertype", opertype);
        return "permission/detail";
    }

    /*编辑权限*/
    @ResponseBody
    @PostMapping("/edit")
    public Map<String, Object> editPermission(@ModelAttribute("permission")Permission permission){
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        int a = permissionService.updateByPermissionId(permission);
        if (a > 0) {
            shiroService.updatePermission();
            return ResultUtil.success();
        } else {
            return ResultUtil.error("保存失败！");
        }
    }

}
