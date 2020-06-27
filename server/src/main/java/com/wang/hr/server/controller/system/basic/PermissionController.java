package com.wang.hr.server.controller.system.basic;

import com.wang.hr.server.model.Menu;
import com.wang.hr.server.model.Result.RespBean;
import com.wang.hr.server.model.Role;
import com.wang.hr.server.service.MenuRoleService;
import com.wang.hr.server.service.MenuService;
import com.wang.hr.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色操作菜单权限
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleService menuRoleService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable("rid") Integer rid){
        return menuRoleService.getMidsByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        if(menuRoleService.updateMenuRole(rid, mids)){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role) == 1){
            return RespBean.ok("添加用户角色成功!");
        }
        return RespBean.error("添加用户角色失败!");
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleByRid(@PathVariable("rid") Integer rid){
        if(roleService.deleteRoleByRid(rid) == 1){
            return RespBean.ok("删除角色成功!");
        }
        return RespBean.error("删除角色失败!");
    }
}
