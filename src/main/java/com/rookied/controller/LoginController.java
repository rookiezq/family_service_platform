package com.rookied.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author zhangqiang
 * @date 2021/8/6
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.POST})
public class LoginController {

    //这是二次验证的请求，可以不用理会
    @RequestMapping("/auth/2step-code")
    public Boolean stepCode(){
        return true;
    }

    @RequestMapping("/auth/login")
    public String login() {
        System.out.println("login");
        return "login";
    }
}
