package com.example.weblogin.adapter.web.v1;

import com.example.weblogin.adapter.web.LoginForm;
import com.example.weblogin.application.usecase.LoginUseCase;
import com.example.weblogin.application.usecase.SessionUseCase;
import com.example.weblogin.domain.Member;
import com.example.weblogin.util.DtoToDomain;
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

    private final LoginUseCase loginUseCase;
    private final SessionUseCase sessionUseCase;

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm
                            , BindingResult bindingResult
                            , HttpServletResponse response){

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginUseCase.login(DtoToDomain.LoginFormToDomain(loginForm));


        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        sessionUseCase.createSession(loginMember,response);
        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionUseCase.expire(request);
        return "redirect:/";
    }

}


