package com.rookied.controller;

import com.rookied.bean.TblUserRecord;
import com.rookied.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println(username+"---"+password);
        TblUserRecord userRecord = loginService.login(username, password);
        System.out.println(userRecord);
        return username;
    }
}
