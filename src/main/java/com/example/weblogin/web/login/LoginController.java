package com.example.weblogin.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm){
        return "login/loginForm";
    }

}
