package com.rookied.controller.fc;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rookied.bean.FcEstate;
import com.rookied.bean.TblCompany;
import com.rookied.returnJson.ReturnObject;
import com.rookied.service.base.FcEstateService;
import com.rookied.service.impl.FcEstateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
}

