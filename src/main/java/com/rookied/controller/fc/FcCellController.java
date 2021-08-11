package com.rookied.controller.fc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookied.bean.FcBuilding;
import com.rookied.bean.FcCell;
import com.rookied.bean.FcUnit;
import com.rookied.returnJson.ReturnObject;
import com.rookied.service.base.FcCellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 房间信息表 前端控制器
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@RestController
@RequestMapping("/fcCell")
public class FcCellController {
    @Autowired
    FcCellService fcCellService;

    @RequestMapping("/insertCells")
    public String insertCells(@RequestBody List<FcUnit> units) {
        List<FcCell> cells = fcCellService.insertAndReturnCells(units);
        boolean b = fcCellService.saveBatch(cells);
        if (b) {
            return new ReturnObject(cells).toString();
        }
        return new ReturnObject("房间插入失败").toString();
    }

    @RequestMapping("/selectCells")
    public String selectCells(String unitCode) {
        QueryWrapper<FcCell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit_code", unitCode);
        List<FcCell> cells = fcCellService.list(queryWrapper);
        return new ReturnObject(cells).toString();
    }

    @RequestMapping("/updateCells")
    public String updateCells(@RequestBody List<FcCell> fcCells) {
        boolean result = fcCellService.updateBatchById(fcCells);
        if (result) {
            return new ReturnObject("房间更新成功").toString();
        }
        return new ReturnObject("房间更新失败").toString();
    }
}

