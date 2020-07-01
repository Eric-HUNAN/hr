package com.wang.hr.server.service;

import com.wang.hr.server.mapper.EmployeeMapper;
import com.wang.hr.server.model.Employee;
import com.wang.hr.server.model.Result.RespPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public RespPage getEmployeeByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope) {
        if(page!=null && size!=null){
            page = (page - 1) * size;
        }
        List<Employee> data = employeeMapper.getEmployeeByPage(page, size, employee, beginDateScope);
        Long total = employeeMapper.getTotal(employee, beginDateScope);
        RespPage respPage = new RespPage();
        respPage.setData(data);
        respPage.setTotal(total);
        return respPage;
    }

    public Integer addEmp(Employee emp) {
        Date beginContract = null;
        Date endContract = null;
        if(emp.getBeginDate()!=null && emp.getConversionTime()!=null){
            beginContract = emp.getBeginDate();
            endContract = emp.getConversionTime();
        }else{
            beginContract = emp.getBeginContract();
            endContract = emp.getEndContract();
        }
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 + (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        emp.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        return employeeMapper.insertSelective(emp);
    }

    public Integer getMaxWorkID() {
        return employeeMapper.getMaxWorkID();
    }

    public Integer deleteEmpByEid(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer addEmps(List<Employee> list) {
        return employeeMapper.addEmps(list);
    }
}
