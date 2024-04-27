package com.example.weblogin.web.session;

import com.example.weblogin.domain.session.SessionRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 * 세션 관리 - 세션생성, 세션조회, 세션만료
 */
@Component
@RequiredArgsConstructor
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private final SessionRepository sessionRepository;

    /**
     * 세션 생성
     */
    public void createSession(Object value, HttpServletResponse response) {

        String mySessionId = UUID.randomUUID().toString();
        sessionRepository.createSession(mySessionId,value);

        Cookie myCookie = new Cookie(SESSION_COOKIE_NAME,mySessionId);
        response.addCookie(myCookie);
    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request) {
        Cookie cookie = findCookie(request);
        if(cookie == null) return null;

        return sessionRepository.getSession(cookie.getValue());
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request);
        if(cookie != null) sessionRepository.removeSession(cookie.getValue());
    }

    private Cookie findCookie(HttpServletRequest request){
        if(request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(SESSION_COOKIE_NAME))
                .findAny()
                .orElse(null);
    }
}
