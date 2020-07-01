package com.wang.hr.server.model.Result;

import java.util.List;

/**
 * 分页查询结果
 */
public class RespPage {
    private Long total;
    private List<?> data;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
