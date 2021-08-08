package com.rookied.service.base;

import com.rookied.bean.FcEstate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rookied.bean.TblCompany;

import java.util.List;

/**
 * <p>
 * 楼盘信息 服务类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
public interface FcEstateService extends IService<FcEstate> {
    List<TblCompany> selectCompany();
}
