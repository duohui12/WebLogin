package com.example.weblogin.adapter.web.v3;

import com.example.weblogin.SessionConst;
import com.example.weblogin.adapter.web.LoginForm;
import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.application.usecase.LoginUseCase;
import com.example.weblogin.domain.Member;
import com.example.weblogin.util.DtoToDomain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginV3Controller {

    private final AuthenticationUseCase authenticationUseCase;

    //@PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult
                            , HttpServletRequest request
                            , HttpServletResponse response){

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //TODO : authenticationUseCase.login() 실행시, 로그인 실패하면 LoginFailException 던짐. 처리하는 로직 필요
        String accessToken = authenticationUseCase.login(DtoToDomain.LoginFormToDomain(loginForm));
        authenticationUseCase.setCookie(accessToken,response);

        return "redirect:/";
    }


}
