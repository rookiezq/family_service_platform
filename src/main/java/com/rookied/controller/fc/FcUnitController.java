package com.rookied.controller.fc;


import com.rookied.bean.FcUnit;
import com.rookied.bean.vo.BuildingMessage;
import com.rookied.returnJson.ReturnObject;
import com.rookied.service.base.FcUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 单元信息表 前端控制器
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@RestController
@RequestMapping("/fcUnit")
public class FcUnitController {
    @Autowired
    FcUnitService fcUnitService;

    @RequestMapping("/insertUnits")
    public String insertBuildings(@RequestBody List<BuildingMessage> buildingMessages) {
        List<FcUnit> units = fcUnitService.insertAndReturnUnits(buildingMessages);
        return new ReturnObject(units).toString();
    }
}

