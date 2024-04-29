package com.example.weblogin.web.login;

import com.example.weblogin.domain.login.LoginService;
import com.example.weblogin.domain.member.Member;
import com.example.weblogin.web.session.SessionManager;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SessionLoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @PostMapping("/sessionLogin")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult
                            , HttpServletResponse response){
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //로그인 성공 -> 세션키 쿠키에 발급 -> home으로 리다이렉트
        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }

}
