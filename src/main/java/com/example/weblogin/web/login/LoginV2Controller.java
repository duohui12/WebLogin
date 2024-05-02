package com.example.weblogin.web.login;

import com.example.weblogin.domain.login.LoginService;
import com.example.weblogin.domain.member.Member;
import com.example.weblogin.web.session.SessionConst;
import com.example.weblogin.web.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
public class LoginV2Controller {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult
                            , HttpServletRequest request){

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //사용자별로 세션이 생성된다.
        //생성된 세션 아이디를 쿠키에 담아준다.
        //요청이 들어올때마다 세션 아이디로 사용자별 세션을 찾는다.
        //해당 세션에 key-value 쌍의 데이터를 저장하고 조회할 수 있다.

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
