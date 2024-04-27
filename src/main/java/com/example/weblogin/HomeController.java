package com.example.weblogin;

import com.example.weblogin.domain.member.Member;
import com.example.weblogin.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final SessionManager sessionManager;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        //세션에 저장된 회원정보 조회
        Member member = (Member)sessionManager.getSession(request);

        //로그인x회원 => 로그인 페이지로
        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }

}
