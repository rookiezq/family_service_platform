package com.rookied.service.base;

import com.rookied.bean.FcUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rookied.bean.vo.BuildingMessage;

import java.util.List;

/**
 * <p>
 * 单元信息表 服务类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
public interface FcUnitService extends IService<FcUnit> {

    List<FcUnit> insertAndReturnUnits(List<BuildingMessage> buildingMessages);
}
