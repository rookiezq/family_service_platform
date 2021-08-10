package com.rookied.controller.fc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookied.bean.FcEstate;
import com.rookied.bean.TblCompany;
import com.rookied.returnJson.ReturnObject;
import com.rookied.service.base.FcEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 楼盘信息 前端控制器
 * </p>
 *
 * @author rookied
 * @since 2021-08-06
 */
@RestController
@RequestMapping("/estate")
public class FcEstateController {
    @Autowired
    FcEstateService fcEstateService;

    @RequestMapping("/selectCompany")
    public String selectCompany() {
        List<TblCompany> companys = fcEstateService.selectCompany();
        return new ReturnObject(companys).toString();
    }

    @RequestMapping("/selectEstate")
    public String selectEstate(String estateCode) {
        QueryWrapper<FcEstate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("estate_code", estateCode);
        FcEstate estate = fcEstateService.getOne(queryWrapper);
        if (estate != null) {
            return new ReturnObject("住宅编码已存在", 0, null).toString();
        }
        return new ReturnObject("该住宅编码可用", 200, null).toString();
    }

    @RequestMapping("/insertEstate")
    public String insertEstate(FcEstate estate) {
        boolean save = fcEstateService.save(estate);
        if (save) {
            return new ReturnObject("1", "房产插入成功").toString();
        }
        return new ReturnObject("0",  "房产插入失败").toString();
    }
}

