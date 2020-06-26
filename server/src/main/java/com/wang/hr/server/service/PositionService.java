package com.wang.hr.server.service;

import com.wang.hr.server.mapper.PositionMapper;
import com.wang.hr.server.model.Position;
import com.wang.hr.server.model.Result.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 职位管理
 */
@Service
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.insertSelective(position);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    public Integer deletePosition(int id) {
        return positionMapper.deleteByPrimaryKey(id);
    }
}
