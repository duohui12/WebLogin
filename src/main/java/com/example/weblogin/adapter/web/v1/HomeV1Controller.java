package com.example.weblogin.adapter.web.v1;

import com.example.weblogin.application.usecase.SessionUseCase;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeV1Controller {

    private final SessionUseCase sessionUseCase;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        //세션에 저장된 회원정보 조회
        Member member = (Member) sessionUseCase.getSession(request);

        //로그인x회원 => 로그인 페이지로
        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }

}
