package com.example.weblogin.adapter.web.v3;

import com.example.weblogin.SessionConst;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class HomeV3Controller {

    //@GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        //TODO : 쿠키에 담긴?? accesstoken 파싱하기
        //TODO : accesstoken 파싱해서 멤버정보 찾고 모델에 담아서 리턴하기

        return null;

    }

}
