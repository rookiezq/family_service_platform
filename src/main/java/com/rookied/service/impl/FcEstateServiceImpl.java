package com.rookied.service.impl;

import com.rookied.bean.FcEstate;
import com.rookied.bean.TblCompany;
import com.rookied.mapper.FcEstateMapper;
import com.rookied.service.base.FcEstateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookied.service.base.TblCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 楼盘信息 服务实现类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@Service
public class FcEstateServiceImpl extends ServiceImpl<FcEstateMapper, FcEstate> implements FcEstateService {

    @Autowired
    TblCompanyService tblCompanyService;

    @Override
    public List<TblCompany> selectCompany() {
        return tblCompanyService.selectCompany();
    }
}
