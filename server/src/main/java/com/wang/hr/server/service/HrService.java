package com.wang.hr.server.service;

import com.wang.hr.server.mapper.HrMapper;
import com.wang.hr.server.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    private HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if(hr == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //写入用户角色
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }
}
