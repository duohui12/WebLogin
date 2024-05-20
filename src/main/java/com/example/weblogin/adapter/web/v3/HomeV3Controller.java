package com.example.weblogin.adapter.web.v3;

import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.application.usecase.FindMemberUseCase;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
public class HomeV3Controller {

    private final AuthenticationUseCase authenticationUseCase;
    private final FindMemberUseCase findMemberUseCase;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){

        //쿠키에 담긴 토큰 decode해서
        Long id = authenticationUseCase.getUserIdFromAccessToken(request);

        //로그인x회원 => 로그인 페이지로
        if(id == null){
            return "home";
        }

        log.info("id : " + id);

        Optional<Member> member = findMemberUseCase.findMemberById(id);
        if(member.isEmpty()) {
            return "home";
        }

        model.addAttribute("member",member.get());
        return "loginHome";

    }
}
