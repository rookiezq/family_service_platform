package com.rookied.service.impl;

import com.rookied.bean.TblCompany;
import com.rookied.mapper.TblCompanyMapper;
import com.rookied.service.base.TblCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业档案 服务实现类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@Service
public class TblCompanyServiceImpl extends ServiceImpl<TblCompanyMapper, TblCompany> implements TblCompanyService {

    @Autowired
    TblCompanyMapper tblCompanyMapper;

    @Override
    public List<TblCompany> selectCompany() {
        return tblCompanyMapper.selectCompany();
    }
}
