package com.rookied.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author zhangqiang
 * @date 2021/8/6
 */
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.POST})
public class LoginController {

    @RequestMapping("/auth/login")
    public String login() {
        System.out.println("login");
        return "login";
    }
}
