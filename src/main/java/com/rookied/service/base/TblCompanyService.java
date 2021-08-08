package com.rookied.service.base;

import com.rookied.bean.TblCompany;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 企业档案 服务类
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
public interface TblCompanyService extends IService<TblCompany> {

     List<TblCompany> selectCompany();
}
