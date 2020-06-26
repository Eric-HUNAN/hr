package com.wang.hr.server.controller.system.basic;


import com.wang.hr.server.model.Position;
import com.wang.hr.server.model.Result.RespBean;
import com.wang.hr.server.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理 --> 职位管理
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPositions();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if(positionService.addPosition(position) == 1){
            return RespBean.ok("添加成功!");
        }else{
            return RespBean.error("添加失败!");
        }
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updatePosition(position) == 1){
            return RespBean.ok("更新成功!");
        }else{
            return RespBean.error("添加失败!");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable("id") int id){
        if(positionService.deletePosition(id) == 1){
            return RespBean.ok("删除成功!");
        }else{
            return RespBean.error("删除失败!");
        }
    }
}
