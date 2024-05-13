package com.example.weblogin.application.usecase;

import com.example.weblogin.domain.LoginAccount;
import com.example.weblogin.domain.Member;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationUseCase {

    public String login(LoginAccount loginAccount);

    public void setCookie(String accessToken, HttpServletResponse response);

    public Long getAccessToken(HttpServletRequest request);
}
