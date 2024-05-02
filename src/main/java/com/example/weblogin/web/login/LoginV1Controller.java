package com.example.weblogin.web.login;

import com.example.weblogin.domain.login.LoginService;
import com.example.weblogin.domain.member.Member;
import com.example.weblogin.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginV1Controller {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    //@PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm
                            , BindingResult bindingResult
                            , HttpServletResponse response){

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        sessionManager.createSession(loginMember,response);
        return "redirect:/";
    }


    //@PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";
    }
}
