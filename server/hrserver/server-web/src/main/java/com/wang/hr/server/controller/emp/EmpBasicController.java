package com.wang.hr.server.controller.emp;

import com.wang.hr.server.model.*;
import com.wang.hr.server.model.Result.RespBean;
import com.wang.hr.server.model.Result.RespPage;
import com.wang.hr.server.service.*;
import com.wang.hr.server.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 员工资料
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private NationService nationService;

    @Autowired
    private PoliticsstatusService politicsstatusService;

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public RespPage getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10")Integer size,
                                      Employee employee,
                                      Date[] beginDateScope){
        return employeeService.getEmployeeByPage(page, size, employee, beginDateScope);
    }

    @PostMapping("/")
    public RespBean addEmp(@RequestBody Employee emp){
        if(employeeService.addEmp(emp) == 1){
            return RespBean.ok("添加成员成功!");
        }
        return RespBean.error("添加成员失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmpByEid(@PathVariable("id") Integer id){
        if(employeeService.deleteEmpByEid(id) == 1){
            return RespBean.ok("删除成员成功!");
        }
        return RespBean.ok("删除成员失败!");
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee){
        if(employeeService.updateEmp(employee) == 1){
            return RespBean.ok("更新成员成功!");
        }
        return RespBean.error("更新成员失败!");
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations(){
        return nationService.getAllNations();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels(){
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean maxWorkID(){
        String id = String.format("%08d", employeeService.getMaxWorkID() + 1);
        return RespBean.build().setStatus(200)
                .setObj(id);
    }

    @GetMapping("/deps")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null, new Employee(), null).getData();
        return POIUtils.employee2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> list = POIUtils.excel2Employee(file, nationService.getAllNations(), politicsstatusService.getAllPoliticsstatus(), departmentService.getAllDepartmentsWithOutChildren(), positionService.getAllPositions(), jobLevelService.getAllJobLevels());
        if (employeeService.addEmps(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}
