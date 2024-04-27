package com.example.weblogin.domain.session;

import com.example.weblogin.domain.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {

    public void createSession(String key, Object value);

    public Object getSession(String key);

    public void removeSession(String key);

}
