package com.wang.hr.server.service;

import com.wang.hr.server.mapper.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    public List<Integer> getMidsByRid(Integer rid) {
        return menuRoleMapper.getMidsByRid(rid);
    }

    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        return result == mids.length;
    }
}
