package com.rookied.service.impl;

import com.rookied.bean.FcBuilding;
import com.rookied.mapper.FcBuildingMapper;
import com.rookied.service.base.FcBuildingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 楼宇信息表 服务实现类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@Service
public class FcBuildingServiceImpl extends ServiceImpl<FcBuildingMapper, FcBuilding> implements FcBuildingService {

    /**
     * 维护楼宇信息，先插入对应数量的楼宇，再将插入好的楼宇信息返回
     * @return 楼宇信息
     */
    @Override
    public List<FcBuilding> insertAndReturnBuildings(String estateCode, Integer buildingNumber) {
        List<FcBuilding> buildingList = new ArrayList<>(buildingNumber);
        for (int i = 0; i < buildingNumber; i++) {
            FcBuilding fcBuilding = new FcBuilding();
            //提前设置好一些属性
            fcBuilding.setBuildingCode("B"+(i+1));
            fcBuilding.setBuildingName((i+1)+"号楼");
            //默认单元数量为1
            fcBuilding.setUnitCount(1);
            fcBuilding.setEstateCode(estateCode);
            //mbp save
            save(fcBuilding);
            buildingList.add(fcBuilding);
        }
        return buildingList;
    }
}
