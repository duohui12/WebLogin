package com.example.weblogin.application.usecase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SessionUseCase {

    public void createSession(Object value, HttpServletResponse response);

    public Object getSession(HttpServletRequest request);

    public void expire(HttpServletRequest request);

}
