package com.wang.hr.server.controller.system.basic;

import com.wang.hr.server.model.Department;
import com.wang.hr.server.model.Result.RespBean;
import com.wang.hr.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dept){
        departmentService.addDep(dept);
        if(dept.getResult() == 1){
            return RespBean.ok("添加部门成功!", dept);
        }
        return RespBean.error("添加部门失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable("id") Integer id){
        Department dept = new Department();
        dept.setId(id);
        departmentService.deleteDepById(dept);
        if(dept.getResult() == -2){
            return RespBean.ok("删除失败，该部门有下属部门!");
        }else if(dept.getResult() == -1){
            return RespBean.ok("删除失败，该部门与部门表关联!");
        }else if(dept.getResult() == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
