package com.example.weblogin.application;

import com.example.weblogin.Exception.LoginFailException;
import com.example.weblogin.application.port.FindMemberPort;
import com.example.weblogin.application.usecase.AuthenticationUseCase;
import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;
import com.example.weblogin.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.CredentialHandlerRuleSet;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {

    public static final String COOKIE_NAME = "myAccessToken";
    private final FindMemberPort findMemberPort;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginAccount loginAccount) {
        Member member = findMemberPort.findByLoginId(loginAccount.getLoginId())
                .filter(m -> m.getPassword().equals(loginAccount.getPassword()))
                .orElseThrow(() -> new LoginFailException(loginAccount.getLoginId()));

        return jwtUtil.encode(member.getId());

    }

    @Override
    public void setCookie(String accessToken, HttpServletResponse response) {
        Cookie myCookie = new Cookie(COOKIE_NAME, accessToken);
        myCookie.setHttpOnly(true); //xss 방지
        myCookie.setSecure(true);
        response.addCookie(myCookie);
    }

    @Override
    public Long getAccessToken(HttpServletRequest request) {
        Cookie cookie = findCookie(request);
        if(cookie == null) return null;

        Claims claims = jwtUtil.decode(cookie.getValue());
        return claims.get("id", Long.class);
    }


    private Cookie findCookie(HttpServletRequest request){
        if(request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                .findAny()
                .orElse(null);
    }
}
