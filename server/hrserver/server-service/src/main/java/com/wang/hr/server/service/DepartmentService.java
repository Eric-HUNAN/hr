package com.wang.hr.server.service;

import com.wang.hr.server.mapper.DepartmentMapper;
import com.wang.hr.server.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDep(Department dept) {
        dept.setEnabled(true);
        departmentMapper.addDep(dept);
    }

    public Integer deleteDepById(Department dept) {
        return departmentMapper.deleteDepById(dept);
    }

    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
