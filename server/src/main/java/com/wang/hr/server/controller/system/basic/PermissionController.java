package com.wang.hr.server.controller.system.basic;

import com.wang.hr.server.model.Role;
import com.wang.hr.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色操作菜单权限
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
}
