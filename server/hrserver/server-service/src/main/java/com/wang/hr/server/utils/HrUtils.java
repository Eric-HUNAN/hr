package com.wang.hr.server.utils;

import com.wang.hr.server.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取当前Hr对象
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
