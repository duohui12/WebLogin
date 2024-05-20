package com.example.weblogin.adapter.web.v2;

import com.example.weblogin.SessionConst;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class HomeV2Controller {

    //@GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        //세션찾기 / false: session이 없을 경우 null반환, true: session이 없을 경우 새로운 세션 생성해서 반환
        HttpSession session = request.getSession(false);
        if(session==null){
            return "home";
        }

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //로그인x회원 => 로그인 페이지로
        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }

}
