package com.wang.hr.server.service;

import com.wang.hr.server.mapper.RoleMapper;
import com.wang.hr.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    public Integer addRole(Role role) {
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insert(role);
    }

    public Integer deleteRoleByRid(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }

    @Transactional
    public Boolean updateHrRole(Integer hrid, Integer[] rids) {
        roleMapper.deleteByHrId(hrid);
        return roleMapper.addRole(hrid, rids) == rids.length;
    }
}
