package com.rookied.service.base;

import com.rookied.bean.FcCell;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rookied.bean.FcUnit;

import java.util.List;

/**
 * <p>
 * 房间信息表 服务类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
public interface FcCellService extends IService<FcCell> {

    List<FcCell> insertAndReturnCells(List<FcUnit> units);
}
