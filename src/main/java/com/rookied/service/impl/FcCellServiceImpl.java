package com.rookied.service.impl;

import com.rookied.bean.FcCell;
import com.rookied.bean.FcUnit;
import com.rookied.mapper.FcCellMapper;
import com.rookied.service.base.FcCellService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 房间信息表 服务实现类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@Service
public class FcCellServiceImpl extends ServiceImpl<FcCellMapper, FcCell> implements FcCellService {


    /**
     * 创建房间 数量=单元数*楼层数*每层房间数
     * @param units 单元信息
     */
    @Override
    public List<FcCell> insertAndReturnCells(List<FcUnit> units) {
        List<FcCell> list = new ArrayList<>();
        for (FcUnit unit : units) {
            for (int i = unit.getStartFloor(); i <= unit.getStopFloor(); i++) {
                for (int j = unit.getStartCellId(); j <= unit.getStopCellId(); j++) {
                    FcCell fcCell = new FcCell();
                    fcCell.setUnitCode(unit.getUnitCode());
                    fcCell.setCellName(i+"0"+j);
                    fcCell.setCellCode(unit.getUnitCode()+"_C"+i+"0"+j);
                    fcCell.setFloorNumber(i);
                    list.add(fcCell);
                }
            }
        }
        return list;
    }
}
