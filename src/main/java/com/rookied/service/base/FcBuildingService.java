package com.rookied.service.base;

import com.rookied.bean.FcBuilding;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 楼宇信息表 服务类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
public interface FcBuildingService extends IService<FcBuilding> {
    List<FcBuilding> insertAndReturnBuildings(String estateCode,Integer buildingNumber);
}
