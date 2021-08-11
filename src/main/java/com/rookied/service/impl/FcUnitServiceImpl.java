package com.rookied.service.impl;

import com.rookied.bean.FcUnit;
import com.rookied.bean.vo.BuildingMessage;
import com.rookied.mapper.FcUnitMapper;
import com.rookied.service.base.FcUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 单元信息表 服务实现类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@Service
public class FcUnitServiceImpl extends ServiceImpl<FcUnitMapper, FcUnit> implements FcUnitService {

    /**
     *  传入多个楼宇信息，每个信息中楼宇编码可能对应多个单元
     * @param buildingMessages 楼宇信息
     * @return 所有单元
     */
    @Override
    public List<FcUnit> insertAndReturnUnits(List<BuildingMessage> buildingMessages) {
        List<FcUnit> units = new ArrayList<>();
        for (BuildingMessage buildingMessage : buildingMessages) {
            Integer unitCount = buildingMessage.getUnitCount();
            if(unitCount == null || unitCount == 0) continue;
            for (int j = 0; j < unitCount; j++) {
                FcUnit fcUnit = new FcUnit();

                //提前设置好一些属性
                fcUnit.setBuildingCode(buildingMessage.getBuildingCode());
                fcUnit.setUnitName("第" + (j + 1) + "单元");
                fcUnit.setUnitCode(buildingMessage.getBuildingCode()+"_U" + (j + 1));
                fcUnit.setStartFloor(1);
                //mbp save
                save(fcUnit);
                units.add(fcUnit);
            }
        }
        return units;
    }
}
