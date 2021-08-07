package com.rookied.controller;

import com.alibaba.fastjson.JSONObject;
import com.rookied.bean.TblUserRecord;
import com.rookied.returnJson.ReturnObject;
import com.rookied.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
        System.out.println("sessionId1:" + session.getId());
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
        System.out.println("sessionId2:" + session.getId());
        TblUserRecord userRecord = (TblUserRecord) session.getAttribute("userRecord");
        //System.out.println(userRecord);
        return "true";
    }
}
