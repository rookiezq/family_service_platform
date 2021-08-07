package com.rookied.controller;

import com.alibaba.fastjson.JSONObject;
import com.rookied.bean.TblUserRecord;
import com.rookied.returnJson.Permission;
import com.rookied.returnJson.Permissions;
import com.rookied.returnJson.ReturnObject;
import com.rookied.returnJson.UserInfo;
import com.rookied.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangqiang
 * @date 2021/8/6
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    //这是二次验证的请求，可以不用理会
    @RequestMapping("/auth/2step-code")
    public Boolean stepCode() {
        return true;
    }

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        System.out.println(username + "---" + password);
        TblUserRecord userRecord = loginService.login(username, password);
        //System.out.println("sessionId1:" + session.getId());
        //保存用户信息到session
        session.setAttribute("userRecord", userRecord);
        //用姓名保存为token
        userRecord.setToken(userRecord.getUserName());
        //生成响应对象
        ReturnObject returnObject = new ReturnObject(userRecord);
        //转为json字符串
        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/users/info")
    public String userInfo(HttpSession session) {
        //System.out.println("sessionId2:" + session.getId());
        TblUserRecord userRecord = (TblUserRecord) session.getAttribute("userRecord");
        //获取用户角色对应的功能模块 格式 roleId1-roleId2-roleId3
        String[] rolePrivileges = userRecord.getTblRole().getRolePrivileges().split("-");
        //拼接所需要返回的数据对象格式
        List<Permission> permissionList = new ArrayList<>();
        for (String rolePrivilege : rolePrivileges) {
            permissionList.add(new Permission(rolePrivilege));
        }
        Permissions permissions = new Permissions();
        permissions.setPermissions(permissionList);
        UserInfo userInfo = new UserInfo(userRecord.getUserName(), permissions);
        ReturnObject returnObject = new ReturnObject(userInfo);
        return JSONObject.toJSONString(returnObject);
    }
}
