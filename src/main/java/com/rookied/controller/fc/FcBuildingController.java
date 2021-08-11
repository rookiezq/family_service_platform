package com.rookied.controller.fc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookied.bean.FcBuilding;
import com.rookied.returnJson.ReturnObject;
import com.rookied.service.base.FcBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 楼宇信息表 前端控制器
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@RestController
@RequestMapping("/fcBuilding")
public class FcBuildingController {
    @Autowired
    FcBuildingService fcBuildingService;


    @RequestMapping("/insertBuildings")
    public String insertBuildings(String estateCode, Integer buildingNumber) {
        List<FcBuilding> buildingList = fcBuildingService.insertAndReturnBuildings(estateCode, buildingNumber);
        return new ReturnObject(buildingList).toString();
    }

    @RequestMapping("/updateBuilding")
    public String updateBuilding(FcBuilding fcBuilding) {
        boolean result = fcBuildingService.updateById(fcBuilding);
        if (result) {
            return new ReturnObject("楼宇更新成功").toString();
        }
        return new ReturnObject("楼宇更新失败").toString();
    }

    @RequestMapping("/updateBuildings")
    public String updateBuildings(@RequestBody List<FcBuilding> fcBuildings) {
        boolean result = fcBuildingService.updateBatchById(fcBuildings);
        if (result) {
            return new ReturnObject("楼宇更新成功").toString();
        }
        return new ReturnObject("楼宇更新失败").toString();
    }

    @RequestMapping("/selectBuildings")
    public String selectBuildings(String estateCode) {
        QueryWrapper<FcBuilding> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code",estateCode);
        queryWrapper.select("building_code","building_name");
        List<FcBuilding> buildingList = fcBuildingService.list(queryWrapper);
        return new ReturnObject(buildingList).toString();
    }
}

