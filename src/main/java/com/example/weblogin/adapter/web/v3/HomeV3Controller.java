package com.example.weblogin.adapter.web.v3;

import com.example.weblogin.SessionConst;
import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class HomeV3Controller {

    private final AuthenticationUseCase authenticationUseCase;

    //@GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        //쿠키에 담긴 토큰 decode해서
        Long userId = authenticationUseCase.getAccessToken(request);

        //로그인x회원 => 로그인 페이지로
        if(userId == null){
            return "home";
        }

        //TODO : userId로 디비에서 member 찾아서
        //model.addAttribute("member",member);

        return "loginHome";

    }
}
